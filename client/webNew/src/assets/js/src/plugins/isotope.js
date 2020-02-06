// Object of plugins to add to Globals.PLUGINS
Globals.PLUGINS.themePluginIsotope = function(context) {
  // ----------------------------------------------------------------
  // Plugin: Isotope (blog/customers grid & sorting)
  // @see: http://isotope.metafizzy.co/
  // Also loads plugin: Imagesloaded (utility for Isotope plugin)
  // @see: https://github.com/desandro/imagesloaded
  // ----------------------------------------------------------------
  var $isoTopes = context.find('[data-toggle=isotope-grid]');
  if ($isoTopes.length > 0) {
    var themePluginIsotopeInit = function() {
      $isoTopes.each(function() {
        var $container = $(this),
          options = $container.data('isotope-options'),
          filters = $container.data('isotope-filter') || null;


        // If imagesLoaded avaliable use it
        if ($document.imagesLoaded) {
          $container.imagesLoaded(function() {
            $container.isotope(options);
          });
        } else {
          $container.isotope(options);
        }

        // Filtering
        if (filters !== null) {
          var $filters = $(filters);
          $filters.on('click', function(e) {
            e.preventDefault();
            $filters.removeClass('active');
            var $this = $(this),
              filterValue = $this.data('isotope-fid') || null;

            if (filterValue) {
              $this.addClass('active');
              $container.isotope({
                filter: filterValue
              });
            }

            return false;
          });
        }

        $('body').addClass('has-isotope');
      });
    };
    $document.themeLoadPlugin(
      ["https://unpkg.com/imagesloaded@4/imagesloaded.pkgd.min.js", "https://unpkg.com/isotope-layout@3/dist/isotope.pkgd.min.js"], [], themePluginIsotopeInit
    );
  }
};


