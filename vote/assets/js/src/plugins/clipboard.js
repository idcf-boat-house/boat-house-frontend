// Object of plugins to add to Globals.PLUGINS
Globals.PLUGINS.themePluginClipboard = function(context) {
  // ----------------------------------------------------------------
  // Plugin: Clipboard.js
  // @see: https://clipboardjs.com/
  // ----------------------------------------------------------------
  var clipboardSelectors = '[data-clipboard-target], [data-clipboard-text]';
  var $clipboards = context.find(clipboardSelectors);
  var themePluginClipboardInit = function() {
    if ($clipboards.length > 0) {
      var clipboard = new Clipboard(clipboardSelectors);
      clipboard.on('success', function(e) {
        var $trigger = $(e.trigger) || null;
        var hasTooltip = $trigger.data('toggle') == 'tooltip' || false;
        if (hasTooltip !== false) {
          var originalTitle = $trigger.data('original-title');
          $trigger.attr("title", "Copied!").tooltip("_fixTitle").tooltip("show").attr("title", originalTitle).tooltip("_fixTitle")
        }
        e.clearSelection();
      });
    }
  };
  
  if ($clipboards.length > 0) {
    $document.themeLoadPlugin(
      ["https://cdnjs.cloudflare.com/ajax/libs/clipboard.js/1.7.1/clipboard.min.js"],
      [],
      themePluginClipboardInit
    );
  }
};


