$(function(){

    $('.showPopup').click(function(e){
        e.preventDefault();

        const tr = $(this).parent().parent();
        const orderProduct  = tr.find('.orderProduct').text();
        const thumb    = tr.find('.thumb1').text();
        const receiver = tr.find('.receiver').text();
        const address  = tr.find('.address').text();
        const pName    = tr.find('.pName').text();
        const price    = tr.find('.price').text();
        const count    = tr.find('.count').text();
        const delivery = tr.find('.delivery').text();
        const total    = tr.find('.total').text();
        const orderer  = tr.find('.orderer').text();
        const date     = tr.find('.date').text();
        
        console.log("thumb : " + thumb);
        console.log("orderProduct : " + orderProduct);
        console.log("receiver : " + receiver);
        console.log("address : " + address);
        console.log("pName : " + pName);
        console.log("price : " + price);
        console.log("count : " + count);
        console.log("delivery : " + delivery);
        console.log("total : " + total);
        console.log("orderer : " + orderer);
        console.log("date : " + date);
        
        const popup = $('#orderPopup');
        popup.find('.orderProduct').text(orderProduct);
        popup.find('.thumb > img').attr('src', '/Farmstory2/thumb/'+thumb);
        popup.find('.receiver').text(receiver);
        popup.find('.address').text(address);
        popup.find('.pName').text(pName);
        popup.find('.price').text(price);
        popup.find('.count').text(count);
        popup.find('.delivery').text(delivery);
        popup.find('.total').text(total);
        popup.find('.orderer').text(orderer);
        popup.find('.date').text(date);

        popup.show();
    });

    $('#orderPopup .btnClose').click(function(){
        $('#orderPopup').hide();
    });
});


$(function(){

    $('.showUserPopup').click(function(e){
        e.preventDefault();
        
        const tr        = $(this).parent().parent();
        const u_uid     = tr.find('.u_uid').text();
        const u_name    = tr.find('.u_name').text();
        const u_nick    = tr.find('.u_nick').text();
        const u_email   = tr.find('.u_email').text();
        const u_hp      = tr.find('.u_hp').text();
        const u_regDate = tr.find('.u_regDate').text();
        const u_role    = tr.find('.u_role').text();
        const u_regip   = tr.find('.u_regip').text();
        const u_addr1   = tr.find('.u_addr1').text();
        const u_addr2   = tr.find('.u_addr2').text();
        
        const popup = $('#userPopup');
        popup.find('.u_uid').text(u_uid);
        popup.find('.u_name').text(u_name);
        popup.find('.u_nick').text(u_nick);
        popup.find('.u_email').text(u_email);
        popup.find('.u_hp').text(u_hp);
        popup.find('.u_role').text(u_role);
        popup.find('.u_addr1').text(u_addr1);
        popup.find('.u_addr2').text(u_addr2);
        popup.find('.u_regip').text(u_regip);
        popup.find('.u_regDate').text(u_regDate);
        
        
        $('#userPopup').show();
    });

    $('#userPopup .btnClose').click(function(){
        $('#userPopup').hide();
    });
});