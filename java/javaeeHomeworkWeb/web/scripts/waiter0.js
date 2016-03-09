/**
 * Created by zzt on 3/1/16.
 */

function waiter0PlanTable() {
    var plan = $('#plan');
    plan.jtable({
        title: 'Your not approved plan',
        paging: true,
        pageSize: 6,
        actions: {
            listAction: 'PlanStaffList',
            deleteAction: 'PlanDelete',
            createAction: 'PlanAdd',
            updateAction: 'PlanWaiterUpdate'
        },
        fields: {
            planId: {
                title: 'Plan Id',
                width: '30%',
                key: true,
                list: false
            },
            planState: {
                title: 'Plan State',
                width: '20%',
                edit: true,
                create: false,
                options: {0: 'New', 2:'Rejected'}
            },
            pdate: {
                title: 'Plan for ',
                width: '20%'
            },
            branch: {
                title: 'Branch',
                width: '30%',
                edit: false,
                options: 'BranchOptions',
                display: function (data) {
                    return data.record.branch.addr;
                }
            },
            details: {
                title: '',
                width: '2%',
                edit: false,
                create: false,
                display: function (planLine) {
                    //Create an image that will be used to open child table
                    var $img = $('<img src="../images/more.png" title="Show plan detail" />');
                    //Open child table when user clicks the image
                    $img.click(function () {
                        $('#plan').jtable('openChildTable',
                            $img.closest('tr'),
                            {
                                title: 'Plan ' + planLine.record.planId + ' - details',
                                actions: {
                                    listAction: 'PlanDetailList?planId=' + planLine.record.planId,
                                    deleteAction: 'PlanDetailDelete',
                                    updateAction: 'PlanDetailUpdate',
                                    createAction: 'PlanDetailAdd?planId=' + planLine.record.planId
                                },
                                fields: {
                                    pdId: {
                                        title: 'Plan detail id',
                                        width: '30%',
                                        key: true,
                                        list: false
                                    },
                                    dessert: {
                                        title: 'Dessert Id',
                                        //options: 'DessertMap',
                                        display: function (data) {
                                            return data.record.dessert.did;
                                        }
                                    },
                                    price: {
                                        title: 'Dessert price'
                                    },
                                    num: {
                                        title: 'Number'
                                    }
                                }
                            },
                            function (data) { //opened handler
                                data.childTable.jtable('load');
                            });
                    });
                    //Return image to show on the person row
                    return $img;
                }
            }
        }
    });
    plan.jtable('load');
}