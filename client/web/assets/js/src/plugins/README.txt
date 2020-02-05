Third-party plugin integration. 

Each plugin integration can be it’s own file and should add to the Globals.PLUGINS variable in order to be autoloaded into AppStrap.

Example: 

Globals.PLUGINS.themePlugin[PLUGINNAME] = function(context) {
  // Integration code here
}


Gulp will render & concat these plugin files automatically into script.js & script.min.js.
To prevent a plugin from being included by gulp simply delete it or replace the .js extension with .txt.

You can also include each plugin file manually by first moving them to /assets/js/ and then adding them to each page footer after the script.js file is included:

<script src="assets/js/script.js"></script>
<script src="assets/js/plugins/fakeLoader.js"></script>

