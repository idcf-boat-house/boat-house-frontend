// ===============================================================
// @group: Base functions that init theme
// ===============================================================

(function($) {
  $.extend($.fn, {

    // ===============================================================
    // Initiates all theme JS
    // ===============================================================
    themeInit: function(refresh) {
      var context = $(this);
      refresh = refresh || false;

      // themePreload: Allow custom callbacks from custom-script.js
      if (typeof $.fn.themePreload === 'function') {
        $.fn.themePreload(context, refresh);
      }

      // Custom
      if (typeof $.fn.themeCustomScripts === 'function') {
        context.themeCustomScripts(refresh);
      }

      // themePrePlugins: Allow custom callbacks from custom-script.js
      if (typeof $.fn.themePrePlugins === 'function') {
        $.fn.themePrePlugins(context, refresh);
      }

      // Plugins
      // Call single plugins like context.themePluginsLoad(false).themePluginSidr();
      var plugins = context.themePluginsLoad(refresh);
      $.each(plugins, function(key, func) {
        if (typeof func === 'function') {
          func(context, refresh);
        }
      });

      // themeLoaded: Allow custom callbacks from custom-script.js
      if (typeof $.fn.themeLoaded === 'function') {
        $.fn.themeLoaded(context, refresh);
      }
    },

    // ===============================================================
    // Refresh scripts after ajax calls
    // ===============================================================
    themeRefresh: function() {
      var context = $(this);
      if (typeof context.context === "undefined" || context.context === null) {
        context.context = context;
      }

      context.themeInit(true);
      context.refreshWaypoints();
    },

    // ===============================================================
    // Third-party plugin intergration/init
    // ===============================================================
    themePluginsLoad: function(refresh) {
      var context = $(this);
      if (typeof context === "undefined" || context === null) {
        context = $(document);
      }
      $document = $(document);

      // Plugin functions
      // name pattern themePluginPLUGINNAME
      // items: PLUGINNAMEs
      //
      // Loaded from plugins.js & custom-script.js
      // ----------------------------------------------------------------
      var plugins = Globals.PLUGINS || {};
      var pluginsDefault = {};
      var pluginsExtras = {};
      if (typeof $.fn.themePlugins !== 'undefined') {
        pluginsDefault = $.fn.themePlugins;
      }
      if (typeof $.fn.themePluginsExtras !== 'undefined') {
        pluginsExtras = $.fn.themePluginsExtras;
      }
      Globals.PLUGINS = $.extend(plugins, pluginsDefault, pluginsExtras);
      return Globals.PLUGINS;
    },
  });
})(jQuery);

$('html').addClass('js');
$(document).ready(function() {
  "use strict";

  // Init theme functions
  $(document).themeInit();
});

