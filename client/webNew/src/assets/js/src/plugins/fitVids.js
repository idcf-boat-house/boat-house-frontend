// Object of plugins to add to Globals.PLUGINS
Globals.PLUGINS.themePluginFitVids = function(context) {
  // ----------------------------------------------------------------
  // Plugin: FitVids.js
  // @see: http://fitvidsjs.com/
  // ----------------------------------------------------------------
  var selectors = [
    "iframe[src*='player.vimeo.com']",
    "iframe[src*='youtube.com']",
    "iframe[src*='youtube-nocookie.com']",
    "iframe[src*='kickstarter.com'][src*='video.html']",
    "object",
    "embed"
  ];
  var $fitVids = context.find(selectors.join(','));
  if ($fitVids.length > 0) {
    var themePluginFitVidsInit = function() {
      $('body').fitVids({
        ignore: '.no-fitvids'
      });
    };
    $document.themeLoadPlugin(["fitvidsjs/jquery.fitvids.js"], [], themePluginFitVidsInit);
  }
};


