$('#new-register-date').datepicker('setDate', new Date());

$(function() {
   $('.js-currency').maskMoney({decimal: ',', thousands: '.', allowZero: true});

   $('.js-category-edit').on('click', function(event) {
       event.preventDefault();
       var button = $(event.currentTarget);
       var receiveUrl = button.attr('href');

       var response = $.ajax({
           url: receiveUrl,
           type: 'GET'
       });

       $('#newCategoryDialog').modal('show');

       var categoryId = $(this).data('id');
       console.log(response)

   });
});

$('#confirmRemoveCategory').on('show.bs.modal', function(event) {
   var button = $(event.relatedTarget);
   var categoryId = button.data('id');
   var categoryDescription = button.data('description');

   var modal = $(this);
   var form = modal.find('form');
   var action = form.data('url-base');

   if(!action.endsWith('/')) {
      action += '/';
   }

   form.attr('action', action + categoryId);
   modal.find('.modal-body span').html('Do you want to remove title <strong>' + categoryDescription + '</strong>?');

});

var changed = false;
$(function() {
    $('input').on('change', function() { // listen to changes on inputs
        changed = true;
        $('.js-export-report').prop('disabled', true); // disabling export button
        console.log(changed);
    });
});

$(function() {
    $('.js-generate-report').on('click', function(event) {
        changed = false;
        event.preventDefault();

        if(validateForm()) {
           getReportData();
            if(!changed) {
                $('.js-export-report').prop('disabled', false); // enabling export button
            }
        }
    });
});

function getReportData() {
    var from = $('#from').val();
    var to = $('#to').val();
    var formData = {from: from, to: to};

    $.ajax({
        url: $('.js-generate-report').attr('href'),
        type: 'POST',
        data: formData,
        success: function (data) {
            $('#reportDataTable').html(data);
        },
        error: function(xhr) {
            console.log(xhr.status);
        }
    });
}

function validateForm() {
    var isFormValid = true;

    $('.required').each(function() {
        var parent = '#' + $(this).closest('div').attr('id');
        if($.trim($(this).val()).length == 0) {
            $(parent).addClass('has-error');
            isFormValid = false;
        } else {
            $(parent).removeClass('has-error');
        }
    });

    return isFormValid;
}

$(function() {
    $('.js-export-report').on('click', function(event) {
        if(!validateForm())
            event.stopPropagation();

        $('.js-export-btn').on('click', function(event) {
            event.preventDefault();

            var formatSelected = $('#export-format-selector option:selected').val();
            console.log(formatSelected);

            $('#format').val(formatSelected);

            $('#exportTypeSelection').modal('hide');
            $('#report-form').submit();
        });
    });
});
