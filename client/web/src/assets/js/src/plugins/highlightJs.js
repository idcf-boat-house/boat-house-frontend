// Object of plugins to add to Globals.PLUGINS
Globals.PLUGINS.themePluginHighlightJS = function(context) {
  // ----------------------------------------------------------------
  // Plugin: highlightjs (code highlighting)
  // @see: https://highlightjs.org
  // ----------------------------------------------------------------
  var $highlightJSs = context.find('code');
  if ($highlightJSs.length > 0) {
    var themePluginHighlightJSInit = function() {
      $('pre code').each(function(i, block) {
        hljs.highlightBlock(block);
      });
    };
    $document.themeLoadPlugin(
      ["https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.12.0/highlight.min.js"], ["https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.12.0/styles/default.min.css", "https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.12.0/styles/github.min.css"],
      themePluginHighlightJSInit
    );
  }
};


