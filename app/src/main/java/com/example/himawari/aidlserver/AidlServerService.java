package com.example.himawari.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.Random;

public class AidlServerService extends Service {
    static String[] handList = {"グー","チョキ","パー"};
    Random r = new Random();

    private IAidlServerService.Stub mStub = new IAidlServerService.Stub() {
        @Override
        public String getMessage(int a) throws RemoteException {
            int b = r.nextInt(3);
            return "相手は"+ handList[b]+" "+ checkJanken(a, b);
        }
    };

    String checkJanken(int a, int b) {
        int c = (a - b + 3) % 3;

        if (c == 0)
            return "DRAW";
        else if (c == 2)
            return "WIN";
        else
            return "LOSE";
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mStub;
    }
}
