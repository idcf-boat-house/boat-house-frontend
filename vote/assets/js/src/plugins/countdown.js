// Object of plugins to add to Globals.PLUGINS
Globals.PLUGINS.themePluginCountDown = function(context) {
  // ----------------------------------------------------------------
  // Plugin: jQuery Countdown timer
  // @see: http://hilios.github.io/jQuery.countdown/
  // ----------------------------------------------------------------
  var $countdowns = context.find('[data-countdown]');
  if ($countdowns.length > 0) {
    var themePluginCountdownInit = function() {
      $countdowns.each(function() {
        var $this = $(this),
          countTo = $this.data('countdown'),
          countdownFormat = $this.data('countdown-format') || null,
          coundownExpireText = $this.data('countdown-expire-text') || null;

        $this.countdown(countTo)
          .on('update.countdown', function(event) {
            if (countdownFormat === null) {
              countdownFormat = '%H hrs %M mins %S secs';
              if (event.offset.totalDays > 0) {
                countdownFormat = '%-d day%!d ' + countdownFormat;
              }
              if (event.offset.weeks > 0) {
                countdownFormat = '%-w week%!w ' + countdownFormat;
              }
            }
            $this.html(event.strftime(countdownFormat));
          })
          .on('finish.countdown', function(event) {
            if (coundownExpireText !== coundownExpireText) {
              $this.html(coundownExpireText);
            }
            $this.addClass('countdown-done');
          });
      });
    };
    $document.themeLoadPlugin(["https://cdnjs.cloudflare.com/ajax/libs/jquery.countdown/2.2.0/jquery.countdown.min.js"], [], themePluginCountdownInit);
  }
};


