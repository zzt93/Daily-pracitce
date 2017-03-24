package memory;

import java.util.Date;
import java.util.List;

/**
 * Created by zzt on 7/20/16.
 * <p>
 * <h3></h3>
 * java -javaagent:./out/artifacts/unnamed/unnamed.jar memory.SizeTest
 */
public class SizeTest {

    private static final long serialVersionUID = 2514272864020277197L;

    /**
     * account info fields
     */
    private int accountId;
    private int shopId;
    @Deprecated
    private int status;
    private int accountType;
    private int buType;  //对应SQL字段BU
    private int cityId;
    private int districtId;
    private boolean isActive;
    private int isSign;
    private int creatorType; //对应SQL字段CreatedByType
    private String mobileNo;
    private String email;
    private int type;
    private String name;
    private int isDeleted;
    private String login;

    private int source;

    /**
     * system fields
     */
    private int id;
    private int addUser; //对应SQL字段createdBy
    private int updateUser; //对应SQL字段lastUpdatedBy
    private Date addTime; //对应SQL字段createdTime
    private Date updateTime; //对应SQL字段lastUpdatedTime

    /**
     * 账号对应的资金池
     */
    private List<Object> adCashPoolDTOs;


    @Deprecated
    private String customerName;

    @Deprecated
    private String companyName;


    public static void main(String[] args) {
        System.out.println(ObjectSizeFetcher.getObjectSize(new SizeTest()));
    }
}
