// Object of plugins to add to Globals.PLUGINS
Globals.PLUGINS.themePluginCountTo = function(context) {
  // ----------------------------------------------------------------
  // Count To (counters)
  // @see: 
  // ----------------------------------------------------------------
  var $countTos = context.find('[data-toggle="count-to"]');
  if ($countTos.length > 0) {
    var themePluginCountToInit = function() {
      $countTos.each(function() {
        var $this = $(this),
          delay = $this.data('delay') || 0;
        $this.waypoint(function() {
          setTimeout(function() {
            $this.countTo({
              onComplete: function() {
                $this.addClass('count-to-done');
              },
              formatter: function(value, options) {
                var v = value.toFixed(options.decimals);
                if (v == '-0') {
                  v = '0';
                }
                return v;
              },
            });
          }, delay);
          this.destroy();
        }, {
          offset: '90%',
        });
      });
    };
    $document.themeLoadPlugin(["https://cdnjs.cloudflare.com/ajax/libs/jquery-countto/1.2.0/jquery.countTo.min.js"], [], function() {
      $document.includeWaypoints(function() {
        $document.isPageLoaderDone(themePluginCountToInit);
      });
    });
  }
};


