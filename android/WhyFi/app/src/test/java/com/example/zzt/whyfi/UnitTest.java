package com.example.zzt.whyfi;

import com.example.zzt.whyfi.common.BytesSetting;
import com.example.zzt.whyfi.model.Message;
import com.example.zzt.whyfi.model.db.Dev;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class UnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertTrue(!Character.isSpaceChar(BytesSetting.SPLIT_BYTE));
    }


    @Test
    public void dateFormat() throws Exception {
        System.out.println(Message.getCurrentTimeStamp());
    }

    @Test
    public void testGUID() throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println(Dev.getGUID());
        }
    }
}