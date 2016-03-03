/**
 * Created by zzt on 3/3/16.
 */


function tableInit () {
    var balance = $('#balanceTable');
    balance.jtable({
        title: 'User balance list',
        paging: true,
        pageSize: 6,
        selecting: true, //Enable selecting
        multiselect: true, //Allow multiple selecting
        selectingCheckboxes: true, //Show checkboxes on first column
        defaultSorting: 'rank ASC',
        actions: {
            listAction: 'UserList'
        },
        fields: {
            rollNo: {
                title: 'User Id',
                width: '30%',
                key: true,
                list: true,
                create: true,
                edit: true
            },
            rank: {
                title: 'balance',
                width: '20%',
                edit: true
            }
        }
    });
    balance.jtable('load');

    var order = $('#order-detail');
    order.jtable({
        title: 'User today order detail',
        defaultSorting: 'rank ASC',
        actions: {
            listAction: 'BranchUserReserveDetailList',
            deleteAction: 'BranchUserReserveDetailDelete'
        },
        fields: {
            dessert: {
                title: 'Dessert',
                width: '20%',
                display: function (data) {
                    return data.record.dessert.name;
                }
            },
            num: {
                title: 'Number',
                width: '30%'
            },
            price: {
                title: 'Price',
                width: '30%'
            },
            rdid: {
                title: 'Reservation Detail Id',
                key: true,
                list: false
            }
        }
    });
    order.jtable('load');
}


function logResponse(response) {
    console.log("Response: " + response);
}

function sendMsg() {
    var lineData = $('#balanceTable').jtable('selectedRows');
    $.post('MsgSend', lineData, logResponse);
    $( this ).dialog( "close" );
}

function pay() {
    var money = $('#money').text();
    $.post('PayMoney', {money:money, type:payType}, logResponse);
    $( this ).dialog( "close" );
}

function getUserInfo(e) {

}