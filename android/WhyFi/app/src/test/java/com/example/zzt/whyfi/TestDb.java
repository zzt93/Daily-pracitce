package com.example.zzt.whyfi;

import com.example.zzt.whyfi.model.db.MsgDbHelper;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by zzt on 6/30/16.
 * <p>
 * Usage:
 */

public class TestDb {

    @Mock
    MsgDbHelper databaseMock;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testQuery()  {
        databaseMock = mock(MsgDbHelper.class);
        when(databaseMock.checkIsDataAlreadyInDBorNot("t1", "", "")).thenReturn(false);
        when(databaseMock.checkIsDataAlreadyInDBorNot(MsgDbHelper.TABLE_MSG, MsgDbHelper.KEY_SELF, "1")).thenReturn(true);

        boolean t1 = databaseMock.checkIsDataAlreadyInDBorNot("t1", "", "");
        boolean t2 = databaseMock.checkIsDataAlreadyInDBorNot(MsgDbHelper.TABLE_MSG, MsgDbHelper.KEY_SELF, "1");
        assertTrue(!t1);
        assertTrue(t2);

    }
}
