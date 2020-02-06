// Object of plugins to add to Globals.PLUGINS
Globals.PLUGINS.themePluginMagnificPopup = function(context) {
  // ----------------------------------------------------------------
  // Plugin: MagnificPopup (popup content)
  // @see: http://dimsemenov.com/plugins/magnific-popup/
  // ----------------------------------------------------------------
  var $magnificPopups = context.find('[data-toggle="magnific-popup"]');
  if ($magnificPopups.length > 0) {
    var themePluginMagnificPopupInit = function() {
      var magnificPopupSettingsDefault = {
        disableOn: 0,
        key: null,
        midClick: false,
        mainClass: 'mfp-fade-zoom',
        preloader: true,
        focus: '', // CSS selector of input to focus after popup is opened
        closeOnContentClick: false,
        closeOnBgClick: true,
        closeBtnInside: true,
        showCloseBtn: true,
        enableEscapeKey: true,
        modal: false,
        alignTop: false,
        removalDelay: 300,
        prependTo: null,
        fixedContentPos: 'auto',
        fixedBgPos: 'auto',
        overflowY: 'auto',
        closeMarkup: '<button title="%title%" type="button" class="mfp-close">&times;</button>',
        tClose: 'Close (Esc)',
        tLoading: 'Loading...',
        type: 'image',
        image: {
          titleSrc: 'data-title',
          verticalFit: true
        }
      };

      $magnificPopups.each(function() {
        var magnificPopupSettings;
        var magnificPopupSettingsExtras = {};
        var $this = $(this);
        var magnificPopupItems = $this.data('magnific-popup-items') || null;

        if ($this.data('magnific-popup-settings') !== '') {
          magnificPopupSettingsExtras = $this.data('magnific-popup-settings');
        }
        magnificPopupSettings = jQuery.extend(magnificPopupSettingsDefault, magnificPopupSettingsExtras);
        
        // Pass items on a single item ie. a "View Gallery" button
        if (magnificPopupItems !== null) {
          var items = [];
          $.each(magnificPopupItems.split(','), function(k, img) {
            items.push({"src": img});
          });
          magnificPopupSettings.items = items;
          magnificPopupSettings.gallery.enabled = true;
          magnificPopupSettings.type = 'image';
          magnificPopupSettings.delegate = null;
        }
        
        $this.magnificPopup(magnificPopupSettings);

        // Transitions
        var mfpImgLoadedClass = 'mfp-image-in';
        $this.on('mfpOpen', function(e /*, params */ ) {
          $.magnificPopup.instance.next = function() {
            var self = this;
            self.wrap.removeClass(mfpImgLoadedClass);
            setTimeout(function() {
              $.magnificPopup.proto.next.call(self);
            }, 120);
          };
          $.magnificPopup.instance.prev = function() {
            var self = this;
            self.wrap.removeClass(mfpImgLoadedClass);
            setTimeout(function() {
              $.magnificPopup.proto.prev.call(self);
            }, 120);
          };
        }).on('mfpImageLoadComplete', function() {
          var $that = $.magnificPopup.instance;
          setTimeout(function() {
            $that.wrap.addClass(mfpImgLoadedClass);
          }, 10);
        });
      });
    };
    $document.themeLoadPlugin(
      ["magnific-popup/dist/jquery.magnific-popup.min.js"],
      ["plugin-css/plugin-magnific-popup.min.css", "magnific-popup/dist/magnific-popup.css"],
      themePluginMagnificPopupInit
    );
  }
};


