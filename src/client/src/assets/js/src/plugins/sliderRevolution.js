// Object of plugins to add to Globals.PLUGINS
Globals.PLUGINS.themePluginSliderRevolution = function(context) {
  // ----------------------------------------------------------------
  // Plugin: Slider Revolution
  // @see: http://codecanyon.net/item/slider-revolution-responsive-jquery-plugin/2580848
  // ----------------------------------------------------------------
  var SLIDER_REV_VERSION = '5.4.4';
  $sliderRevolutions = context.find('[data-toggle=slider-rev]');
  if ($sliderRevolutions.length > 0) {
    var themePluginSliderRevolutionInit = function() {
      if ($sliderRevolutions.length === 0) {
        $sliderRevolutions = context.find('[data-toggle=slider-rev]');
      }

      $sliderRevolutions.each(function() {
        var sliderRevEl = $(this);
        var customInit = sliderRevEl.data('custom-init') || false;
        sliderRevEl.data('version', SLIDER_REV_VERSION);

        var slides = sliderRevEl.find('li') || 0;
        var pluginsLocation = $document.getScriptLocation();
        var sliderRevSettingsDefaults = {
          extensions: 'slider-revolution/revolution/js/extensions/',
          jsFileLocation: pluginsLocation,
          responsiveLevels: [1240, 1024, 778, 480],
          visibilityLevels: [1240, 1024, 778, 480],
          spinner: 'spinner5',
          lazyType: "smart",
          navigation: {
            arrows: {
              enable: slides.length > 1 ? true : false,
              style: 'appstrap',
              tmp: '',
              rtl: false,
              hide_onleave: false,
              hide_onmobile: true,
              hide_under: 481,
              hide_over: 9999,
              hide_delay: 200,
              hide_delay_mobile: 1200,
              left: {
                container: 'slider',
                h_align: 'left',
                v_align: 'center',
                h_offset: 20,
                v_offset: 0
              },
              right: {
                container: 'slider',
                h_align: 'right',
                v_align: 'center',
                h_offset: 20,
                v_offset: 0
              },
            },
          },
        };
        var sliderRevSettings;
        sliderRevSettings = $.extend(sliderRevSettingsDefaults, sliderRevEl.data('settings'));

        if (customInit) {
          sliderRevEl.addClass('custom-init').trigger("appstrap:sliderRev:customInit", [sliderRevSettings]);
          return;
        } else {
          sliderRevEl.hide();
          var sliderAPI = sliderRevEl.addClass('standard-init').show().revolution(sliderRevSettings);
          sliderRevEl.trigger("appstrap:sliderRev:standardInit", [sliderRevSettings]);

          // Pause sliders on modals open
          $('.modal').on('shown.bs.modal', function() {
            if (sliderAPI) {
              sliderAPI.revpause();
            }
          }).on('hidden.bs.modal', function(e) {
            if (sliderAPI) {
              sliderAPI.revresume();
            }
          });
        }
      });
    };

    $document.themeLoadPlugin(
      ["slider-revolution/revolution/js/jquery.themepunch.tools.min.js?v=" + SLIDER_REV_VERSION,
        "slider-revolution/revolution/js/source/jquery.themepunch.revolution.js?v=" + SLIDER_REV_VERSION
      ], ["plugin-css/plugin-slider-revolution.min.css", "slider-revolution/revolution/css/settings.css?v=" + SLIDER_REV_VERSION],
      function() {
        $document.isPageLoaderDone(themePluginSliderRevolutionInit);
      }
    );
  }
};


