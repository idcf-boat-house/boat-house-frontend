// Object of plugins to add to Globals.PLUGINS
Globals.PLUGINS.themePluginNiceScroll = function(context) {
  // ----------------------------------------------------------------
  // Plugin: Custom Scrollbar
  // @see: https://github.com/inuyaksa/jquery.nicescroll
  // ----------------------------------------------------------------
  var $scrollbars = context.find('[data-toggle="scrollbar"]');
  if ($scrollbars.length > 0) {
    var themePluginCustomScrollbarInit = function() {
      $scrollbars.each(function() {
        var $this = $(this);
        var settings = $this.data('settings') || {
          "emulatetouch": true
        };
        $this.niceScroll(settings);
      });
    };

    $document.themeLoadPlugin(
      ["https://cdnjs.cloudflare.com/ajax/libs/jquery.nicescroll/3.7.6/jquery.nicescroll.min.js"], [],
      themePluginCustomScrollbarInit
    );
  }
};


