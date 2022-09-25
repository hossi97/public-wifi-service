$(".deleteBtn").click(function () {
    let checkBtn = $(this);
    let tr = checkBtn.parent().parent();
    let td = tr.children();
    let historyId = td.eq(0).text();

    //서블릿 호출
    $.ajax({
        type: "post",
        url : "http://localhost:8080/RemoveHistory?historyId=" + historyId  // 포트 변경하기, 여러개는 & 이용
    }).done(function () {
        location.reload();
    })
})