// Object of plugins to add to Globals.PLUGINS
Globals.PLUGINS.themePluginTyped = function(context) {
  // ----------------------------------------------------------------
  // typed.js - typewriter effect
  // @see: https://github.com/mattboldt/typed.js
  // ----------------------------------------------------------------
  var $typeds = context.find('[data-typed]');
  if ($typeds.length > 0) {
    var themePluginTypedInit = function() {
      $typeds.each(function() {
        var $this = $(this),
          typedStrings = $this.data('typed') || null,
          typedSettings = $this.data('typed-settings') || {},
          typedDelay = typedSettings.delay || 0;
        typedSettings.autoStart = true;
        typedSettings.callback = function() {
          if (typedSettings.doneClass !== '') {
            $.each(typedSettings.doneClass, function(e, c) {
              $(e).addClass(c);
            });
          }
        };

        if (typedStrings !== '') {
          if (typeof typedStrings === 'object') {
            typedSettings.strings = typedStrings;
          }
          $this.waypoint(function() {
            setTimeout(function() {
              $this.typeIt(typedSettings);
            }, typedDelay);
            this.destroy();
          }, {
            offset: '100%',
          });
        }
      });
    };

    $document.themeLoadPlugin(["https://cdn.jsdelivr.net/jquery.typeit/4.4.0/typeit.min.js"], [], function() {
      $document.includeWaypoints(function() {
        $document.isPageLoaderDone(themePluginTypedInit);
      });
    });
  }
};


