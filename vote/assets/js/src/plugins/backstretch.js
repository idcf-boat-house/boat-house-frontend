// Object of plugins to add to Globals.PLUGINS
Globals.PLUGINS.themePluginBackstretch = function(context) {
  // ----------------------------------------------------------------
  // Plugin: Backstretch
  // @see: http://srobbin.com/jquery-plugins/backstretch/
  // ----------------------------------------------------------------
  var $backstretches = context.find('[data-toggle=backstretch]');
  if ($backstretches.length > 0) {
    var themePluginBackstretchInit = function() {
      $backstretches.each(function() {
        var backstretchEl = $(this);
        var backstretchTarget = jQuery,
          backstretchImgs = [];
        var backstretchSettings = {
          fade: 750,
          duration: 4000
        };

        // Get images from element
        jQuery.each(backstretchEl.data('backstretch-imgs').split(','), function(k, img) {
          backstretchImgs[k] = img;
        });

        // block level element
        if (backstretchEl.data('backstretch-target')) {
          backstretchTarget = backstretchEl.data('backstretch-target');
          if (backstretchTarget === 'self') {
            backstretchTarget = backstretchEl;
          } else {
            if ($(backstretchTarget).length > 0) {
              backstretchTarget = $(backstretchTarget);
            }
          }
        }

        if (backstretchImgs) {
          $('html').addClass('has-backstretch');

          // Merge in any custom settings
          backstretchSettings = $.extend({}, backstretchSettings, backstretchEl.data());
          backstretchTarget.backstretch(backstretchImgs, backstretchSettings);

          // add overlay
          if (backstretchEl.data('backstretch-overlay') !== false) {
            $('.backstretch').prepend('<div class="backstretch-overlay"></div>');

            if (backstretchEl.data('backstretch-overlay-opacity')) {
              $('.backstretch').find('.backstretch-overlay').css('background', 'white').fadeTo(0, backstretchEl.data('backstretch-overlay-opacity'));
            }
          }
        }
      });
    };
    $document.themeLoadPlugin(["backstretch/jquery.backstretch.min.js"], [], themePluginBackstretchInit);
  }
};


