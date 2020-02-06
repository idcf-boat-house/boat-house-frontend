// Object of plugins to add to Globals.PLUGINS
Globals.PLUGINS.themePluginFlexslider = function(context) {
  // ----------------------------------------------------------------
  // Plugin: flexslider
  // @see: http://www.woothemes.com/flexslider/
  //
  // To be deprecated in AppStrap 3.0.14
  // ----------------------------------------------------------------
  var $flexsliders = context.find('.flexslider');
  if ($flexsliders.length > 0) {
    var themePluginFlexsliderInit = function() {
      $flexsliders.each(function() {
        var sliderSettings = {
          animation: jQuery(this).attr('data-transition'),
          selector: ".slides > .slide",
          controlNav: true,
          smoothHeight: true,
          start: function(slider) {
            //hide all animated elements
            slider.find('[data-animate-in]').each(function() {
              jQuery(this).css('visibility', 'hidden');
            });

            //slide backgrounds
            slider.find('.slide-bg').each(function() {
              jQuery(this).css({
                'background-image': 'url(' + jQuery(this).data('bg-img') + ')'
              });
              jQuery(this).css('visibility', 'visible').addClass('animated').addClass(jQuery(this).data('animate-in'));
            });

            //animate in first slide
            slider.find('.slide').eq(1).find('[data-animate-in]').each(function() {
              jQuery(this).css('visibility', 'hidden');
              if (jQuery(this).data('animate-delay')) {
                jQuery(this).addClass(jQuery(this).data('animate-delay'));
              }
              if (jQuery(this).data('animate-duration')) {
                jQuery(this).addClass(jQuery(this).data('animate-duration'));
              }
              jQuery(this).css('visibility', 'visible').addClass('animated').addClass(jQuery(this).data('animate-in'));
              jQuery(this).one('webkitAnimationEnd oanimationend msAnimationEnd animationend',
                function() {
                  jQuery(this).removeClass(jQuery(this).data('animate-in'));
                }
              );
            });
          },
          before: function(slider) {
            slider.find('.slide-bg').each(function() {
              jQuery(this).removeClass(jQuery(this).data('animate-in')).removeClass('animated').css('visibility', 'hidden');
            });

            //hide next animate element so it can animate in
            slider.find('.slide').eq(slider.animatingTo + 1).find('[data-animate-in]').each(function() {
              jQuery(this).css('visibility', 'hidden');
            });
          },
          after: function(slider) {
            //hide animtaed elements so they can animate in again
            slider.find('.slide').find('[data-animate-in]').each(function() {
              jQuery(this).css('visibility', 'hidden');
            });

            //animate in next slide
            slider.find('.slide').eq(slider.animatingTo + 1).find('[data-animate-in]').each(function() {
              if (jQuery(this).data('animate-delay')) {
                jQuery(this).addClass(jQuery(this).data('animate-delay'));
              }
              if (jQuery(this).data('animate-duration')) {
                jQuery(this).addClass(jQuery(this).data('animate-duration'));
              }
              jQuery(this).css('visibility', 'visible').addClass('animated').addClass(jQuery(this).data('animate-in'));
              jQuery(this).one('webkitAnimationEnd oanimationend msAnimationEnd animationend',
                function() {
                  jQuery(this).removeClass(jQuery(this).data('animate-in'));
                }
              );
            });

            $(window).trigger('resize');

          }
        };

        var sliderNav = jQuery(this).attr('data-slidernav');
        if (sliderNav !== 'auto') {
          sliderSettings = $.extend({}, sliderSettings, {
            manualControls: sliderNav + ' li a',
            controlsContainer: '.flexslider-wrapper'
          });
        }

        jQuery('html').addClass('has-flexslider');
        jQuery(this).flexslider(sliderSettings);
        jQuery('.flexslider').resize(); //make sure height is right load assets loaded
      });
    };
    $document.themeLoadPlugin(["flexslider/jquery.flexslider-min.js"], ["plugin-css/plugin-flexslider.min.css", "flexslider/flexslider.css"], themePluginFlexsliderInit);
  }
};


