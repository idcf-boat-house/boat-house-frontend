// Object of plugins to add to Globals.PLUGINS
// Add custom plugin integration here or as separate files
// within the /src/plugins directory
var defaultPlugins = {
  //themePluginPLUGINNAME: function(context) {
  //  // Example: Plugin integration code here
  //},
  // Next plugin here
};

// Add to Globals.PLUGINS
$.extend($.fn, {themePlugins: defaultPlugins});

