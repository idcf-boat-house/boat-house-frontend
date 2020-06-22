// Object of plugins to add to Globals.PLUGINS
Globals.PLUGINS.themePluginOwlCarousel = function(context) {
  // ----------------------------------------------------------------
  // Plugin: OwlCarousel (carousel displays)
  // @see: http://owlgraphic.com/owlcarousel/
  // ----------------------------------------------------------------
  var $owlCarousels = context.find('[data-toggle="owl-carousel"]');
  var $owlCarouselThumbs = context.find('[data-owl-carousel-thumbs]');
  if ($owlCarousels.length > 0) {
    var themePluginOwlCarouselInit = function(context) {
      $owlCarousels.each(function() {
        var $owlCarousel = $(this),
          owlCarouselSettings = $owlCarousel.data('owl-carousel-settings') || null;

        $owlCarousel.addClass('owl-carousel').owlCarousel(owlCarouselSettings);
      });

      $owlCarouselThumbs.each(function() {
        var $owlThumbsWrap = $(this),
          $owlThumbs = $owlThumbsWrap.find('.owl-thumb'),
          $owlTarget = $($owlThumbsWrap.data('owl-carousel-thumbs')) || null,
          owlThumbsCarousel = $owlThumbsWrap.data('toggle') !== '' && $owlThumbsWrap.data('toggle') == 'owl-carousel' || false;

        if ($owlTarget) {
          $owlThumbsWrap.find('owl-item').removeClass('active');
          $owlThumbs.removeClass('active');
          $owlThumbs.eq(0).addClass('active');
          $owlThumbs.on('click', function(event) {
            $owlTarget.trigger('to.owl.carousel', [$(this).parent().index(), 300, true]);

          });
          if (owlThumbsCarousel) {
            $owlThumbsWrap.owlCarousel();
          }

          // Owl API
          $owlTarget.owlCarousel();
          $owlTarget.on('changed.owl.carousel', function(event) {
            var item = event.item.index;
            $owlThumbs.removeClass('active');
            $owlThumbs.eq(item).addClass('active');

            if (owlThumbsCarousel) {
              if (event.namespace && event.property.name === 'position') {
                var target = event.relatedTarget.relative(event.property.value, true);
                $owlThumbsWrap.owlCarousel('to', target, 300, true);
              }
            }
          });
        }
      });
    };
    $document.themeLoadPlugin(
      ["https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.2.1/owl.carousel.min.js"],
      ["plugin-css/plugin-owl-carousel.min.css", "https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.2.1/assets/owl.carousel.min.css", "https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css"],
      themePluginOwlCarouselInit
    );
  }
};


