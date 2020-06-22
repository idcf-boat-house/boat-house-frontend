/**
 * SyntaxHighlighter
 * http://alexgorbatchev.com/SyntaxHighlighter
 *
 * SyntaxHighlighter is donationware. If you are using it, please donate.
 * http://alexgorbatchev.com/SyntaxHighlighter/donate.html
 *
 * @version
 * 3.0.83 (July 02 2010)
 * 
 * @copyright
 * Copyright (C) 2004-2010 Alex Gorbatchev.
 *
 * @license
 * Dual licensed under the MIT and GPL licenses.
 */(function(){function e(){var e="break case catch continue default delete do else false  for function if in instanceof new null return super switch this throw true try typeof var while with",t=SyntaxHighlighter.regexLib;this.regexList=[{regex:t.multiLineDoubleQuotedString,css:"string"},{regex:t.multiLineSingleQuotedString,css:"string"},{regex:t.singleLineCComments,css:"comments"},{regex:t.multiLineCComments,css:"comments"},{regex:/\s*#.*/gm,css:"preprocessor"},{regex:new RegExp(this.getKeywords(e),"gm"),css:"keyword"}];this.forHtmlScript(t.scriptScriptTags)}typeof require!="undefined"?SyntaxHighlighter=require("shCore").SyntaxHighlighter:null;e.prototype=new SyntaxHighlighter.Highlighter;e.aliases=["js","jscript","javascript"];SyntaxHighlighter.brushes.JScript=e;typeof exports!="undefined"?exports.Brush=e:null})();