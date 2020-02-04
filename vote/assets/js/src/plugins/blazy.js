// Object of plugins to add to Globals.PLUGINS
Globals.PLUGINS.themePluginBlazy = function(context) {
  // ----------------------------------------------------------------
  // Plugin: Blazy
  // @see: http://dinbror.dk/blog/blazy/
  //
  // From AppStrap 3.0.9 data-bg-img is now loaded by BLazy too
  // ----------------------------------------------------------------          
  var $blazys = context.find('[data-toggle="blazy"]');
  var $blazy_bgs = context.find('[data-bg-img]');
  var bLazy;
  if ($blazys.length > 0 || $blazy_bgs.length > 0) {
    var themePluginBlazyInit = function() {
      if ($blazys.length > 0) {
        $blazys.each(function() {
          // Remove alts while loading
          var $t = $(this);
          var alt = $t.attr('alt') || null;
          if (alt) {
            $t.data('alt', alt);
            $t.attr('alt', '');
          }
        });

        bLazy = new Blazy({
          selector: '[data-toggle="blazy"]',
          loadInvisible: true,
          success: function(ele) {
            var $ele = $(ele);
            var alt = $ele.data('alt') || null;
            if (alt) {
              $ele.attr('alt', alt);
              $ele.data('alt', '');
            }
          }
        });
      }

      if ($blazy_bgs.length > 0) {
        $blazy_bgs.addClass('bg-img blazy-bg');
        bLazy = new Blazy({
          selector: '[data-bg-img]',
          loadInvisible: true,
          src: 'data-bg-img'
        });
      }
    };

    $document.themeLoadPlugin(
      ["https://cdn.jsdelivr.net/blazy/latest/blazy.min.js"], ["plugin-css/plugin-blazy.min.css"],
      themePluginBlazyInit
    );
  }
};


