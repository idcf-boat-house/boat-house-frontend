// Object of plugins to add to Globals.PLUGINS
Globals.PLUGINS.themePluginDropdown = function(context) {
  // ----------------------------------------------------------------
  // Plugin: Bootstrap Hover Dropdown
  // @see: https://github.com/CWSpear/bootstrap-hover-dropdown
  // ----------------------------------------------------------------
  var $dropdowns = context.find('[data-hover="dropdown"]');
  if ($dropdowns.length > 0) {
    $document.themeLoadPlugin(["bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"], [], null, 'append');
  }
};


