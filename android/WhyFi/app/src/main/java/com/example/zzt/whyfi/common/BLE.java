package com.example.zzt.whyfi.common;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.zzt.whyfi.model.Message;
import com.example.zzt.whyfi.vm.MsgHistory;
import com.polidea.rxandroidble.RxBleClient;
import com.polidea.rxandroidble.RxBleConnection;
import com.polidea.rxandroidble.RxBleScanResult;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zzt on 6/5/16.
 * <p>
 * Usage:
 */
public class BLE {

    private static final String CANONICAL_NAME = BLE.class.getCanonicalName();
    public static UUID BLE_CHAT_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
//    public static UUID BLE_NAME_UUID = UUID.fromString("ble name uuid");
    private static Context context;
    private static Handler handler = new Handler(Looper.getMainLooper());

    public static void init(Context context) {
        if (rxBleClient == null) {
            BLE.context = context;
            rxBleClient = RxBleClient.create(BLE.context);
        }
    }


    private static RxBleClient rxBleClient;

    /**
     * read one characteristic from every connection
     * <p>
     * collect surrounding message and add to
     * {@link com.example.zzt.whyfi.vm.MsgHistory}
     * <p>
     * called from non-ui thread to collection message repeatedly
     *
     * @return scan result subscription
     */
    public static Subscription readMsg() {
        assert context != null;

        final CompositeSubscription compositeSubscription = new CompositeSubscription();

        Subscription subscribe = rxBleClient
                .scanBleDevices()
                .subscribe(new Action1<RxBleScanResult>() {
                    @Override
                    public void call(RxBleScanResult rxBleScanResult) {
                        // connection made event source
                        Observable<RxBleConnection> connectionSrc = rxBleScanResult
                                .getBleDevice()
                                .establishConnection(context, false);


                        Subscription conSubscription = connectionSrc
                                .flatMap(new Func1<RxBleConnection, Observable<byte[]>>() {
                                    @Override
                                    public Observable<byte[]> call(final RxBleConnection rxBleConnection) {
                                        return rxBleConnection.readCharacteristic(BLE.BLE_CHAT_UUID);
                                    }
                                })
                                .take(1)
                                .subscribe(new Action1<byte[]>() {
                                    @Override
                                    public void call(final byte[] bytes) {
                                        handler.post(
                                                new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        try {
                                                            MsgHistory.addReceived(Message.getFromBytes(bytes));
                                                        } catch (UnsupportedEncodingException e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                }
                                        );
                                        Log.d(CANONICAL_NAME, new String(bytes));
                                    }
                                });
//                compositeSubscription.add(conSubscription);
                    }
                });
        compositeSubscription.add(subscribe);
        return compositeSubscription;
    }

    /**
     * calling from ui-thread
     * @return scan result subscription
     */
    public static Subscription writeReadMsg(final Message message) {

        CompositeSubscription compositeSubscription = new CompositeSubscription();
        Subscription subscribe = rxBleClient.scanBleDevices().subscribe(new Action1<RxBleScanResult>() {
            @Override
            public void call(RxBleScanResult rxBleScanResult) {
                // connection made event source
                Observable<RxBleConnection> connectionSrc = rxBleScanResult
                        .getBleDevice()
                        .establishConnection(context, false);

                connectionSrc
                        .flatMap(new Func1<RxBleConnection, Observable<byte[]>>() {
                            @Override
                            public Observable<byte[]> call(final RxBleConnection rxBleConnection) {

                                return rxBleConnection.readCharacteristic(BLE.BLE_CHAT_UUID)
                                        /**
                                         * doOnNext is for side-effects: you want to react (eg. log) to item emissions
                                         * in an intermediate step of your stream
                                         */
                                        .doOnNext(new Action1<byte[]>() {
                                            @Override
                                            public void call(byte[] bytes) {
                                                try {
                                                    MsgHistory.addReceived(Message.getFromBytes(bytes));
                                                } catch (UnsupportedEncodingException e) {
                                                    e.printStackTrace();
                                                }
                                                Log.d(CANONICAL_NAME, new String(bytes));
                                            }
                                        })
                                        .flatMap(new Func1<byte[], Observable<byte[]>>() {
                                            @Override
                                            public Observable<byte[]> call(byte[] bytes) {
                                                try {
                                                    return rxBleConnection.writeCharacteristic(BLE.BLE_CHAT_UUID,
                                                            message.toBytes());
                                                } catch (UnsupportedEncodingException e) {
                                                    e.printStackTrace();
                                                    return null;
                                                }
                                            }
                                        });
                            }
                        })
                        .subscribe(new Action1<byte[]>() {
                            @Override
                            public void call(byte[] bytes) {
                                Log.d(CANONICAL_NAME, new String(bytes));
                            }
                        });

                connectionSrc.flatMap(new Func1<RxBleConnection, Observable<byte[]>>() {
                    @Override
                    public Observable<byte[]> call(RxBleConnection rxBleConnection) {
                        return rxBleConnection.readCharacteristic(BLE_CHAT_UUID);
                    }
                }).subscribe(new Action1<byte[]>() {
                    @Override
                    public void call(byte[] bytes) {

                    }
                });

                connectionSrc.flatMap(new Func1<RxBleConnection, Observable<byte[]>>() {
                    @Override
                    public Observable<byte[]> call(RxBleConnection rxBleConnection) {
                        try {
                            return rxBleConnection.writeCharacteristic(BLE_CHAT_UUID, message.toBytes());
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }
                }).subscribe(new Action1<byte[]>() {
                    @Override
                    public void call(byte[] bytes) {

                    }
                });
            }
        });
        compositeSubscription.add(subscribe);
        return compositeSubscription;
    }


}