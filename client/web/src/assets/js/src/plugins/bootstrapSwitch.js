// Object of plugins to add to Globals.PLUGINS
Globals.PLUGINS.themePluginBootstrapSwitch = function(context) {
  // ----------------------------------------------------------------
  // Plugin: Bootstrap switch integration
  // @see: http://www.bootstrap-switch.org/
  // ----------------------------------------------------------------
  var $bootstrapSwitches = context.find('[data-toggle=switch]');
  if ($bootstrapSwitches.length > 0) {
    var themePluginBootstrapSwitchInit = function() {
      $bootstrapSwitches.bootstrapSwitch();
    };
    $document.themeLoadPlugin(
      ["bootstrap-switch/build/js/bootstrap-switch.min.js"], ["plugin-css/plugin-bootstrap-switch.min.css", "bootstrap-switch/build/css/bootstrap3/bootstrap-switch.min.css"],
      themePluginBootstrapSwitchInit
    );
  }
};


