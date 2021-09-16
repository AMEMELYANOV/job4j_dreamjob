$(document).ready(getCitiesToForm())

function getCitiesToForm() {
    $.ajax({
        type: 'GET',
        crossdomain: true,
        url: 'http://localhost:8080/dreamjob/cities.do',
        dataType: 'json',
    }).done(function (data) {
        for (const [key, value] of Object.entries(data)) {
            let str;
            if (key === $('#currentCity').text()) {
                str = '<option selected value="' + key + '">' + value + '</>';
            } else {
                str = '<option  value="' + key + '">' + value + '</>';
            }
            $('#selectedCity').append(str);
        }
    }).fail(function (err) {
        alert(err);
    });
}

function validate() {
    let rsl = true;
    let el = $(`#inputName`);
    if (el.val() === '') {
        alert(el.attr('title'));
        rsl = false;
    }
    return rsl;
}