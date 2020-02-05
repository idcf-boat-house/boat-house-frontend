// Object of plugins to add to Globals.PLUGINS
Globals.PLUGINS.themePluginFakeLoader = function(context) {
  // ----------------------------------------------------------------
  // fakeLoader.js - page loading indicator/icon
  // @see: http://joaopereirawd.github.io/fakeLoader.js/
  // NOTE: This can effect SEO
  // ----------------------------------------------------------------
  var $fakeLoaders = context.find('[data-toggle=page-loader]');
  if ($fakeLoaders.length > 0) {
    $('html').addClass('has-page-loader');

    var themePluginFakeLoaderInit = function() {
      var $pageLoader = jQuery('[data-toggle=page-loader]'),
        options = {
          zIndex: 9999999,
          spinner: $pageLoader.data('spinner') || 'spinner6',
          timeToHide: 1000
        };
      $pageLoader.fakeLoader(options);
      //$('body').removeClass('page-loader-spacer');

      $document.isPageLoaderDone(function() {
        $('html').removeClass('has-page-loader');
        $(window).trigger('resize');
      });
    };
    $document.themeLoadPlugin(["fakeLoader/fakeLoader.min.js"], ["fakeLoader/fakeLoader.css"], themePluginFakeLoaderInit);
  }
};


