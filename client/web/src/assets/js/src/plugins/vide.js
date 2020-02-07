// Object of plugins to add to Globals.PLUGINS
Globals.PLUGINS.themePluginVide = function(context) {
  // ----------------------------------------------------------------
  // Plugin: Video Backgrounds
  // @see: https://github.com/VodkaBears/Vide
  // ----------------------------------------------------------------
  var $vides = context.find('[data-bg-video]');
  if ($vides.length > 0) {
    var themePluginVideInit = function() {
      $vides.each(function() {
        var videoBg = $(this);
        var videoBgVideos = videoBg.data('bg-video') || null;
        var videoBgOptions = videoBg.data('settings') || {};
        var videoBgDefaultOptions = {
          'className': 'bg-video-video'
        };
        videoBgOptions = jQuery.extend(videoBgDefaultOptions, videoBgOptions);

        if (videoBgVideos !== null) {
          videoBg.addClass('bg-video').vide(videoBgVideos, videoBgOptions);
        }
      });
    };
    $document.themeLoadPlugin(['https://cdnjs.cloudflare.com/ajax/libs/vide/0.5.1/jquery.vide.min.js'], [], themePluginVideInit);
  }
};


