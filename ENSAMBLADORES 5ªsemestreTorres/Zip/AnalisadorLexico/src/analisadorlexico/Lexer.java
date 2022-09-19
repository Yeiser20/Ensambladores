/* The following code was generated by JFlex 1.4.3 on 1/12/21 05:53 PM */

package analisadorlexico;

import static analisadorlexico.Tokens.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 1/12/21 05:53 PM from the specification file
 * <tt>C:/Users/danie/OneDrive/Documentos/5ª SEMESTRE FACULTAD/ENSAMBLADORES/Zip/AnalisadorLexico/src/analisadorlexico/Lexer.flex</tt>
 */
class Lexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\5\1\104\2\0\1\5\22\0\1\10\1\6\1\50\1\6"+
    "\1\54\1\6\1\0\1\45\1\44\1\46\2\6\1\13\1\60\1\6"+
    "\1\0\1\61\11\4\1\6\1\103\3\0\1\6\1\0\1\27\1\11"+
    "\1\40\1\16\1\32\1\2\1\33\1\47\1\70\1\100\1\43\1\66"+
    "\1\34\1\35\1\41\1\71\1\7\1\73\1\31\1\30\1\76\1\75"+
    "\1\15\1\64\1\7\1\102\4\1\1\3\1\1\1\20\1\12\1\36"+
    "\1\17\1\23\1\2\1\24\1\62\1\67\1\77\1\42\1\65\1\25"+
    "\1\26\1\37\1\56\1\2\1\72\1\22\1\21\1\55\1\74\1\14"+
    "\1\63\1\57\1\101\1\52\1\51\1\53\43\0\1\6\35\0\1\6"+
    "\uff40\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\4\1\1\2\1\3\3\4\2\5\2\4\32\1"+
    "\1\3\1\6\2\0\4\7\1\10\2\4\1\11\1\4"+
    "\1\11\1\5\2\4\1\7\1\12\1\11\1\7\1\12"+
    "\1\7\1\11\14\7\1\11\1\7\1\11\3\0\1\7"+
    "\1\0\16\7\1\13\3\7\3\0\3\7\1\13\2\7"+
    "\1\13\2\7\1\13\2\7\1\0\1\14\2\0\1\15"+
    "\1\0\6\7\1\16\1\17\2\0\4\7\1\20\3\7"+
    "\1\2\1\21\1\0\1\21\1\10\2\7\1\22\5\0"+
    "\1\16\6\0\1\23\15\0\1\20\6\0";

  private static int [] zzUnpackAction() {
    int [] result = new int[182];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\105\0\212\0\317\0\u0114\0\u0159\0\u019e\0\u01e3"+
    "\0\u0228\0\u026d\0\u02b2\0\u02f7\0\u033c\0\u0381\0\u03c6\0\u040b"+
    "\0\u0450\0\u0495\0\u04da\0\u051f\0\u0564\0\u05a9\0\u05ee\0\u0633"+
    "\0\u0678\0\u06bd\0\u0702\0\u0747\0\u078c\0\u07d1\0\u0816\0\u085b"+
    "\0\u08a0\0\u08e5\0\u092a\0\u096f\0\u09b4\0\u09f9\0\u0a3e\0\u0a83"+
    "\0\u0ac8\0\105\0\212\0\u0b0d\0\317\0\u0b52\0\u0114\0\u0b97"+
    "\0\u0b97\0\u0bdc\0\u0c21\0\317\0\u0c66\0\u0114\0\u0cab\0\u0cf0"+
    "\0\u0d35\0\u0d7a\0\u0114\0\u0dbf\0\u0e04\0\317\0\u0e49\0\u0e8e"+
    "\0\u0ed3\0\u0f18\0\u0f5d\0\u0fa2\0\u0fe7\0\u102c\0\u1071\0\u10b6"+
    "\0\u10fb\0\u1140\0\u1185\0\u11ca\0\u1185\0\u120f\0\u102c\0\u1254"+
    "\0\u1299\0\u12de\0\u1323\0\u1368\0\u13ad\0\u13f2\0\u1437\0\u147c"+
    "\0\u14c1\0\u1506\0\u154b\0\u1590\0\u09b4\0\u15d5\0\u161a\0\u165f"+
    "\0\u16a4\0\u16e9\0\317\0\u172e\0\u1773\0\u17b8\0\u17fd\0\u1842"+
    "\0\u1887\0\u18cc\0\u1911\0\u1956\0\u0114\0\u199b\0\u19e0\0\u1a25"+
    "\0\u1a6a\0\u1aaf\0\u1af4\0\u1b39\0\u1b7e\0\u1bc3\0\105\0\u1c08"+
    "\0\u1c4d\0\u1c92\0\u1cd7\0\u1d1c\0\u1d61\0\u1da6\0\u1deb\0\u1e30"+
    "\0\u1e75\0\u17fd\0\u1eba\0\u1eff\0\u1f44\0\u1f89\0\u1fce\0\u2013"+
    "\0\u2058\0\317\0\u209d\0\u20e2\0\u2127\0\105\0\105\0\u216c"+
    "\0\u1c4d\0\105\0\u21b1\0\u21f6\0\u1eff\0\u223b\0\u2280\0\u22c5"+
    "\0\u230a\0\u234f\0\u223b\0\u2394\0\u23d9\0\u241e\0\u2463\0\u24a8"+
    "\0\u24ed\0\u2532\0\u2577\0\u25bc\0\u2601\0\u2646\0\u268b\0\u26d0"+
    "\0\u2715\0\u275a\0\u279f\0\u27e4\0\u2829\0\u286e\0\u28b3\0\105"+
    "\0\u28f8\0\u293d\0\u2982\0\u29c7\0\u2a0c\0\u2a51";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[182];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\1\7\1\2\1\5"+
    "\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16"+
    "\1\17\1\4\1\20\1\21\1\4\1\22\1\4\1\23"+
    "\1\5\1\24\1\25\1\4\1\26\1\4\1\27\1\4"+
    "\1\30\3\4\1\31\1\32\1\2\1\4\1\33\4\2"+
    "\1\5\1\34\1\4\1\35\1\6\1\4\1\36\1\37"+
    "\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\4"+
    "\2\5\1\47\1\50\1\4\1\5\1\51\1\52\106\0"+
    "\1\53\1\0\1\53\3\0\1\53\2\0\1\53\2\0"+
    "\4\53\7\0\2\53\4\0\1\53\5\0\1\54\10\0"+
    "\2\53\5\0\1\53\4\0\1\53\1\0\1\53\1\0"+
    "\2\53\3\0\1\53\4\0\2\55\1\56\2\0\1\55"+
    "\1\0\2\55\1\0\30\55\3\0\1\55\5\0\3\55"+
    "\1\0\1\56\21\55\3\0\1\53\1\55\1\57\1\56"+
    "\2\0\1\57\1\0\1\55\1\57\1\0\1\55\4\57"+
    "\7\55\2\57\4\55\1\57\5\55\1\54\2\0\1\55"+
    "\5\0\2\57\1\55\1\0\1\56\2\55\1\57\4\55"+
    "\1\57\1\55\1\57\1\55\2\57\3\55\1\57\4\0"+
    "\2\60\1\6\2\0\1\60\1\0\2\60\1\0\30\60"+
    "\3\0\1\61\5\0\3\60\1\0\1\6\1\61\20\60"+
    "\7\0\1\7\2\0\1\7\2\0\1\7\73\0\2\55"+
    "\1\56\2\0\1\55\1\0\2\62\1\63\2\55\2\62"+
    "\24\55\3\0\1\64\5\0\3\55\1\0\1\56\2\55"+
    "\1\64\1\55\1\64\2\55\1\64\11\55\3\0\1\53"+
    "\1\55\1\57\1\56\2\0\1\57\1\0\1\62\1\65"+
    "\1\63\1\55\1\57\2\65\1\57\7\55\2\57\4\55"+
    "\1\57\5\55\1\54\2\0\1\55\5\0\1\57\1\66"+
    "\1\55\1\0\1\56\2\64\1\57\1\64\3\55\1\57"+
    "\1\55\1\57\1\55\2\57\3\55\1\57\7\0\1\7"+
    "\2\0\1\7\2\63\1\12\2\67\2\70\67\0\2\55"+
    "\1\56\2\0\1\55\1\0\2\55\1\67\4\13\24\55"+
    "\3\0\1\55\5\0\3\55\1\0\1\56\21\55\3\0"+
    "\1\53\1\55\1\57\1\56\2\0\1\57\1\0\1\55"+
    "\1\57\1\67\1\13\3\14\1\57\7\55\2\57\4\55"+
    "\1\57\5\55\1\54\2\0\1\55\5\0\2\57\1\55"+
    "\1\0\1\56\2\55\1\57\4\55\1\57\1\55\1\57"+
    "\1\55\2\57\3\55\1\57\3\0\1\53\1\55\1\57"+
    "\1\56\2\0\1\57\1\0\1\62\1\65\1\70\1\13"+
    "\1\14\2\71\1\57\6\55\1\72\1\57\1\73\4\55"+
    "\1\57\5\55\1\54\2\0\1\64\5\0\2\57\1\55"+
    "\1\0\1\56\2\55\1\66\1\55\1\64\1\55\1\74"+
    "\1\57\1\55\1\57\1\55\2\57\3\55\1\57\3\0"+
    "\1\53\1\55\1\57\1\56\2\0\1\57\1\0\1\62"+
    "\1\65\1\70\1\13\1\14\2\71\1\75\1\55\1\76"+
    "\5\55\2\57\4\55\1\57\5\55\1\54\2\0\1\55"+
    "\5\0\1\77\1\57\1\55\1\0\1\56\2\64\1\57"+
    "\1\64\1\55\1\100\1\55\1\57\1\55\1\57\1\55"+
    "\2\57\3\55\1\57\3\0\1\53\1\55\1\57\1\56"+
    "\2\0\1\57\1\0\1\55\1\57\1\0\1\55\2\57"+
    "\2\101\7\55\2\57\4\55\1\57\5\55\1\54\2\0"+
    "\1\55\5\0\2\57\1\55\1\0\1\56\2\64\1\57"+
    "\1\64\3\55\1\57\1\55\1\57\1\55\2\57\3\55"+
    "\1\57\4\0\2\55\1\56\2\0\1\55\1\0\2\55"+
    "\1\0\5\55\1\102\1\76\21\55\3\0\1\55\5\0"+
    "\1\55\1\64\1\55\1\0\1\56\1\103\4\55\1\64"+
    "\13\55\4\0\2\55\1\56\2\0\1\55\1\0\2\55"+
    "\1\0\6\55\1\76\3\55\1\104\15\55\3\0\1\55"+
    "\5\0\3\55\1\0\1\56\21\55\4\0\2\55\1\56"+
    "\2\0\1\55\1\0\2\55\1\0\23\55\1\105\4\55"+
    "\3\0\1\55\5\0\3\55\1\0\1\56\21\55\4\0"+
    "\2\55\1\56\2\0\1\55\1\0\2\55\1\0\2\55"+
    "\1\106\10\55\1\106\14\55\3\0\1\64\5\0\3\55"+
    "\1\0\1\56\2\55\1\64\1\55\1\64\14\55\3\0"+
    "\1\53\1\55\1\57\1\56\2\0\1\57\1\0\1\55"+
    "\1\57\1\0\1\55\4\57\7\55\1\107\1\73\4\55"+
    "\1\57\5\55\1\54\2\0\1\110\5\0\2\57\1\55"+
    "\1\0\1\56\2\55\1\57\3\55\1\64\1\66\1\55"+
    "\1\57\1\55\2\57\3\55\1\57\4\0\2\55\1\56"+
    "\2\0\1\55\1\0\2\55\1\0\15\55\1\76\3\55"+
    "\1\111\6\55\3\0\1\55\5\0\3\55\1\0\1\56"+
    "\21\55\4\0\2\55\1\56\2\0\1\55\1\0\2\55"+
    "\1\0\25\55\1\112\2\55\3\0\1\55\5\0\3\55"+
    "\1\0\1\56\21\55\3\0\1\53\1\55\1\57\1\56"+
    "\2\0\1\57\1\0\1\55\1\57\1\0\1\113\4\57"+
    "\7\55\2\57\4\55\1\57\1\114\4\55\1\54\2\0"+
    "\1\55\5\0\2\57\1\55\1\0\1\56\2\64\1\57"+
    "\1\115\3\55\1\57\1\55\1\57\1\55\2\57\3\55"+
    "\1\57\4\0\2\55\1\56\2\0\1\55\1\0\2\55"+
    "\1\0\1\55\1\106\13\55\1\76\7\55\1\116\2\55"+
    "\3\0\1\64\5\0\3\55\1\0\1\56\2\55\1\64"+
    "\1\55\1\117\14\55\62\0\1\120\26\0\1\121\3\0"+
    "\37\121\1\0\2\121\4\0\5\121\1\0\21\121\4\0"+
    "\1\122\3\0\37\122\1\0\2\122\4\0\5\122\1\0"+
    "\21\122\3\0\1\53\1\55\1\57\1\56\2\0\1\57"+
    "\1\0\1\55\1\57\1\0\1\55\4\57\7\55\2\57"+
    "\4\55\1\57\1\123\4\55\1\54\2\0\1\55\5\0"+
    "\2\57\1\55\1\0\1\56\2\55\1\57\4\55\1\57"+
    "\1\55\1\57\1\55\2\57\3\55\1\57\63\0\1\124"+
    "\25\0\2\55\1\56\2\0\1\55\1\0\2\55\1\0"+
    "\22\55\1\125\5\55\3\0\1\55\5\0\3\55\1\0"+
    "\1\56\21\55\3\0\1\53\1\55\1\57\1\56\2\0"+
    "\1\57\1\0\1\55\1\57\1\0\1\55\4\57\7\55"+
    "\2\57\4\55\1\57\1\55\1\126\3\55\1\54\2\0"+
    "\1\55\5\0\2\57\1\55\1\0\1\56\2\55\1\57"+
    "\4\55\1\57\1\55\1\57\1\55\2\57\3\55\1\57"+
    "\4\0\2\55\1\56\2\0\1\55\1\0\2\55\1\0"+
    "\3\55\1\127\17\55\1\130\4\55\3\0\1\55\5\0"+
    "\3\55\1\0\1\56\21\55\4\0\2\55\1\56\2\0"+
    "\1\55\1\0\2\55\1\0\2\55\1\131\22\55\1\132"+
    "\2\55\3\0\1\55\5\0\3\55\1\0\1\56\21\55"+
    "\4\0\2\55\1\56\2\0\1\55\1\0\2\55\1\0"+
    "\11\55\1\133\1\134\15\55\3\0\1\55\5\0\3\55"+
    "\1\0\1\56\10\55\1\135\10\55\4\0\2\55\1\56"+
    "\2\0\1\55\1\0\2\55\1\0\20\55\1\136\1\137"+
    "\6\55\3\0\1\55\5\0\3\55\1\0\1\56\11\55"+
    "\1\140\7\55\3\0\1\53\1\55\1\57\1\56\2\0"+
    "\1\57\1\0\1\55\1\57\1\0\1\55\4\57\7\55"+
    "\2\57\4\55\1\57\2\55\1\141\2\55\1\54\2\0"+
    "\1\55\5\0\2\57\1\55\1\0\1\56\2\55\1\57"+
    "\4\55\1\57\1\55\1\57\1\55\2\57\3\55\1\57"+
    "\4\0\2\55\1\56\2\0\1\55\1\0\2\55\1\0"+
    "\7\55\1\134\20\55\3\0\1\55\5\0\3\55\1\0"+
    "\1\56\21\55\3\0\1\53\1\55\1\57\1\56\2\0"+
    "\1\57\1\0\1\55\1\57\1\0\1\55\4\57\7\55"+
    "\2\57\1\137\3\55\1\57\5\55\1\54\2\0\1\55"+
    "\5\0\2\57\1\55\1\0\1\56\2\55\1\57\4\55"+
    "\1\57\1\55\1\57\1\55\2\57\3\55\1\57\4\0"+
    "\2\55\1\56\2\0\1\55\1\0\2\55\1\0\4\55"+
    "\1\142\1\55\1\143\3\55\1\127\7\55\1\144\5\55"+
    "\3\0\1\55\5\0\3\55\1\0\1\56\3\55\1\143"+
    "\15\55\4\0\2\55\1\56\2\0\1\55\1\0\2\55"+
    "\1\0\13\55\1\145\1\55\1\143\3\55\1\131\2\55"+
    "\1\146\3\55\3\0\1\55\5\0\3\55\1\0\1\56"+
    "\4\55\1\143\14\55\2\0\104\51\3\0\1\147\1\0"+
    "\1\150\1\0\37\147\1\151\2\147\4\0\5\147\1\150"+
    "\21\147\6\0\1\56\54\0\1\56\25\0\2\60\3\0"+
    "\1\60\1\0\2\60\1\0\30\60\3\0\1\60\5\0"+
    "\3\60\2\0\21\60\4\0\2\55\1\56\2\0\1\55"+
    "\1\0\2\62\1\63\2\55\2\62\24\55\3\0\1\55"+
    "\5\0\3\55\1\0\1\56\21\55\13\0\3\63\2\0"+
    "\2\63\66\0\1\53\1\55\1\57\1\56\2\0\1\57"+
    "\1\0\1\62\1\65\1\63\1\55\1\57\2\65\1\57"+
    "\7\55\2\57\4\55\1\57\5\55\1\54\2\0\1\55"+
    "\5\0\2\57\1\55\1\0\1\56\2\55\1\57\4\55"+
    "\1\57\1\55\1\57\1\55\2\57\3\55\1\57\15\0"+
    "\5\67\76\0\2\63\1\70\2\67\2\70\66\0\1\53"+
    "\1\55\1\57\1\56\2\0\1\57\1\0\1\62\1\65"+
    "\1\70\1\13\1\14\2\71\1\57\7\55\2\57\4\55"+
    "\1\57\5\55\1\54\2\0\1\55\5\0\2\57\1\55"+
    "\1\0\1\56\2\55\1\57\4\55\1\57\1\55\1\57"+
    "\1\55\2\57\3\55\1\57\4\0\2\55\1\56\2\0"+
    "\1\55\1\0\2\55\1\0\14\55\1\152\13\55\3\0"+
    "\1\55\5\0\3\55\1\0\1\56\21\55\4\0\2\55"+
    "\1\56\2\0\1\55\1\0\2\55\1\0\30\55\3\0"+
    "\1\55\5\0\3\55\1\0\1\56\13\55\1\143\5\55"+
    "\3\0\1\53\1\55\1\57\1\56\2\0\1\57\1\0"+
    "\1\55\1\57\1\0\1\55\4\57\1\153\6\55\2\57"+
    "\4\55\1\57\5\55\1\54\2\0\1\55\5\0\2\57"+
    "\1\55\1\0\1\56\2\55\1\57\4\55\1\57\1\55"+
    "\1\57\1\55\2\57\3\55\1\57\3\0\1\53\1\55"+
    "\1\57\1\56\2\0\1\57\1\0\1\55\1\57\1\0"+
    "\1\55\4\57\7\55\2\57\4\55\1\57\5\55\1\54"+
    "\2\0\1\55\5\0\1\57\1\154\1\55\1\0\1\56"+
    "\2\55\1\57\4\55\1\57\1\55\1\57\1\55\2\57"+
    "\3\55\1\57\4\0\2\55\1\56\2\0\1\55\1\0"+
    "\2\55\1\0\30\55\3\0\1\55\5\0\3\55\1\0"+
    "\1\56\12\55\1\143\6\55\3\0\1\53\1\55\1\57"+
    "\1\56\2\0\1\57\1\0\1\55\1\57\1\0\1\55"+
    "\2\57\1\155\1\57\7\55\2\57\4\55\1\57\5\55"+
    "\1\54\2\0\1\55\5\0\2\57\1\55\1\0\1\56"+
    "\2\55\1\57\4\55\1\57\1\55\1\57\1\55\2\57"+
    "\3\55\1\57\4\0\2\55\1\56\2\0\1\55\1\0"+
    "\2\55\1\0\3\55\1\143\1\156\23\55\3\0\1\55"+
    "\5\0\3\55\1\0\1\56\21\55\4\0\2\55\1\56"+
    "\2\0\1\55\1\0\2\55\1\0\30\55\3\0\1\55"+
    "\5\0\3\55\1\0\1\56\3\55\1\143\15\55\4\0"+
    "\2\55\1\56\2\0\1\55\1\0\2\55\1\0\3\55"+
    "\1\157\24\55\3\0\1\55\5\0\3\55\1\0\1\56"+
    "\21\55\4\0\2\55\1\56\2\0\1\55\1\0\2\55"+
    "\1\0\30\55\3\0\1\55\5\0\3\55\1\0\1\56"+
    "\12\55\1\160\6\55\4\0\2\55\1\56\2\0\1\55"+
    "\1\0\2\55\1\0\2\55\1\143\25\55\3\0\1\55"+
    "\5\0\3\55\1\0\1\56\21\55\3\0\1\53\1\55"+
    "\1\57\1\56\2\0\1\57\1\0\1\55\1\57\1\0"+
    "\1\55\1\57\1\155\2\57\6\55\1\161\2\57\4\55"+
    "\1\57\5\55\1\54\2\0\1\55\5\0\2\57\1\55"+
    "\1\0\1\56\2\55\1\57\4\55\1\57\1\55\1\57"+
    "\1\55\2\57\3\55\1\57\4\0\2\55\1\56\2\0"+
    "\1\55\1\0\2\55\1\0\30\55\3\0\1\55\5\0"+
    "\3\55\1\0\1\56\4\55\1\143\14\55\4\0\2\55"+
    "\1\56\2\0\1\55\1\0\2\55\1\0\2\55\1\162"+
    "\25\55\3\0\1\55\5\0\3\55\1\0\1\56\21\55"+
    "\4\0\2\55\1\56\2\0\1\55\1\0\2\55\1\0"+
    "\30\55\3\0\1\55\5\0\3\55\1\0\1\56\13\55"+
    "\1\163\5\55\4\0\2\55\1\56\2\0\1\55\1\0"+
    "\2\55\1\0\3\55\1\143\24\55\3\0\1\55\5\0"+
    "\3\55\1\0\1\56\21\55\4\0\2\55\1\56\2\0"+
    "\1\55\1\0\2\55\1\0\3\55\1\164\24\55\3\0"+
    "\1\55\5\0\3\55\1\0\1\56\21\55\4\0\2\55"+
    "\1\56\2\0\1\55\1\0\2\55\1\0\2\55\1\165"+
    "\25\55\3\0\1\55\5\0\3\55\1\0\1\56\21\55"+
    "\6\0\1\166\54\0\1\166\25\0\1\121\3\0\37\121"+
    "\1\167\2\121\4\0\1\170\4\121\1\0\21\121\4\0"+
    "\1\122\3\0\37\122\1\0\2\122\1\171\3\0\1\172"+
    "\4\122\1\0\21\122\4\0\2\55\1\56\2\0\1\55"+
    "\1\0\2\55\1\0\30\55\3\0\1\55\5\0\1\55"+
    "\1\143\1\55\1\0\1\56\21\55\6\0\1\173\54\0"+
    "\1\173\25\0\2\55\1\56\2\0\1\55\1\0\2\55"+
    "\1\0\30\55\3\0\1\55\5\0\3\55\1\0\1\56"+
    "\1\174\20\55\4\0\2\55\1\56\2\0\1\55\1\0"+
    "\2\55\1\0\30\55\3\0\1\175\5\0\3\55\1\0"+
    "\1\56\21\55\4\0\2\55\1\56\2\0\1\55\1\0"+
    "\2\55\1\0\6\55\1\143\21\55\3\0\1\55\5\0"+
    "\3\55\1\0\1\56\21\55\4\0\2\55\1\56\2\0"+
    "\1\55\1\0\2\55\1\0\23\55\1\176\4\55\3\0"+
    "\1\55\5\0\3\55\1\0\1\56\21\55\4\0\2\55"+
    "\1\56\2\0\1\55\1\0\2\55\1\0\15\55\1\143"+
    "\12\55\3\0\1\55\5\0\3\55\1\0\1\56\21\55"+
    "\4\0\2\55\1\56\2\0\1\55\1\0\2\55\1\0"+
    "\25\55\1\177\2\55\3\0\1\55\5\0\3\55\1\0"+
    "\1\56\21\55\4\0\2\55\1\56\2\0\1\55\1\0"+
    "\2\55\1\0\30\55\3\0\1\55\5\0\1\103\2\55"+
    "\1\0\1\56\21\55\4\0\2\55\1\56\2\0\1\55"+
    "\1\0\2\55\1\0\5\55\1\143\22\55\3\0\1\55"+
    "\5\0\3\55\1\0\1\56\21\55\4\0\2\55\1\56"+
    "\2\0\1\55\1\0\2\55\1\0\30\55\3\0\1\55"+
    "\5\0\3\55\1\0\1\56\14\55\1\110\4\55\4\0"+
    "\2\55\1\56\2\0\1\55\1\0\2\55\1\0\14\55"+
    "\1\143\13\55\3\0\1\55\5\0\3\55\1\0\1\56"+
    "\21\55\4\0\2\55\1\56\2\0\1\55\1\0\2\55"+
    "\1\0\16\55\1\137\11\55\3\0\1\55\5\0\3\55"+
    "\1\0\1\56\21\55\4\0\2\55\1\56\2\0\1\55"+
    "\1\0\2\55\1\0\30\55\3\0\1\55\5\0\3\55"+
    "\1\0\1\56\7\55\1\143\11\55\4\0\2\55\1\56"+
    "\2\0\1\55\1\0\2\55\1\0\7\55\1\143\20\55"+
    "\3\0\1\55\5\0\3\55\1\0\1\56\21\55\4\0"+
    "\2\55\1\56\2\0\1\55\1\0\2\55\1\0\30\55"+
    "\3\0\1\55\5\0\3\55\1\0\1\56\1\55\1\200"+
    "\17\55\4\0\2\55\1\56\2\0\1\55\1\0\2\55"+
    "\1\0\16\55\1\143\11\55\3\0\1\55\5\0\3\55"+
    "\1\0\1\56\21\55\4\0\2\55\1\56\2\0\1\55"+
    "\1\0\2\55\1\0\30\55\3\0\1\55\5\0\3\55"+
    "\1\0\1\56\2\55\1\201\16\55\4\0\1\147\3\0"+
    "\37\147\1\0\1\202\1\147\4\0\5\147\1\0\21\147"+
    "\6\0\1\150\41\0\1\203\1\204\11\0\1\150\25\0"+
    "\1\205\3\0\37\205\1\0\2\205\4\0\5\205\1\0"+
    "\21\205\4\0\2\55\1\56\2\0\1\55\1\0\2\55"+
    "\1\0\13\55\1\206\14\55\3\0\1\55\5\0\3\55"+
    "\1\0\1\56\21\55\4\0\2\55\1\56\2\0\1\55"+
    "\1\0\2\55\1\0\4\55\1\207\23\55\3\0\1\55"+
    "\5\0\3\55\1\0\1\56\21\55\3\0\1\53\1\55"+
    "\1\57\1\56\2\0\1\57\1\0\1\55\1\57\1\0"+
    "\1\55\4\57\7\55\2\57\4\55\1\57\5\55\1\54"+
    "\2\0\1\55\5\0\2\57\1\210\1\0\1\56\2\55"+
    "\1\57\4\55\1\57\1\55\1\57\1\55\2\57\3\55"+
    "\1\57\4\0\2\55\1\56\2\0\1\55\1\0\2\55"+
    "\1\0\22\55\1\211\5\55\3\0\1\55\5\0\3\55"+
    "\1\0\1\56\21\55\4\0\2\55\1\56\2\0\1\55"+
    "\1\0\2\55\1\0\6\55\1\212\21\55\3\0\1\55"+
    "\5\0\1\55\1\212\1\55\1\0\1\56\21\55\4\0"+
    "\2\55\1\56\2\0\1\55\1\0\2\55\1\0\6\55"+
    "\1\213\21\55\3\0\1\55\5\0\3\55\1\0\1\56"+
    "\21\55\4\0\2\55\1\56\2\0\1\55\1\0\2\55"+
    "\1\0\24\55\1\214\3\55\3\0\1\55\5\0\3\55"+
    "\1\0\1\56\21\55\4\0\2\55\1\56\2\0\1\55"+
    "\1\0\2\55\1\0\15\55\1\212\12\55\3\0\1\55"+
    "\5\0\3\55\1\0\1\56\7\55\1\212\11\55\4\0"+
    "\2\55\1\56\2\0\1\55\1\0\2\55\1\0\15\55"+
    "\1\215\12\55\3\0\1\55\5\0\3\55\1\0\1\56"+
    "\21\55\4\0\2\55\1\56\2\0\1\55\1\0\2\55"+
    "\1\0\7\55\1\207\20\55\3\0\1\55\5\0\3\55"+
    "\1\0\1\56\21\55\4\0\2\55\1\56\2\0\1\55"+
    "\1\0\2\55\1\0\16\55\1\206\11\55\3\0\1\55"+
    "\5\0\3\55\1\0\1\56\21\55\6\0\1\166\41\0"+
    "\1\216\12\0\1\166\25\0\1\121\3\0\37\121\1\217"+
    "\2\121\4\0\1\170\4\121\1\0\21\121\50\0\1\220"+
    "\40\0\1\122\3\0\37\122\1\0\2\122\1\221\3\0"+
    "\1\172\4\122\1\0\21\122\6\0\1\173\42\0\1\222"+
    "\11\0\1\173\25\0\2\55\1\56\2\0\1\55\1\0"+
    "\2\55\1\0\10\55\1\143\17\55\3\0\1\55\5\0"+
    "\3\55\1\0\1\56\21\55\4\0\2\55\1\56\2\0"+
    "\1\55\1\0\2\55\1\0\17\55\1\143\10\55\3\0"+
    "\1\55\5\0\3\55\1\0\1\56\21\55\4\0\2\55"+
    "\1\56\2\0\1\55\1\0\2\55\1\0\30\55\3\0"+
    "\1\55\5\0\1\55\1\223\1\55\1\0\1\56\21\55"+
    "\4\0\2\55\1\56\2\0\1\55\1\0\2\55\1\0"+
    "\30\55\3\0\1\55\5\0\3\55\1\0\1\56\7\55"+
    "\1\224\11\55\4\0\2\55\1\56\2\0\1\55\1\0"+
    "\2\55\1\0\30\55\3\0\1\55\5\0\3\55\1\0"+
    "\1\56\17\55\1\143\1\55\4\0\2\55\1\56\2\0"+
    "\1\55\1\0\2\55\1\0\30\55\3\0\1\55\5\0"+
    "\3\55\1\0\1\56\20\55\1\143\50\0\1\203\104\0"+
    "\1\225\40\0\1\205\3\0\37\205\1\226\2\205\4\0"+
    "\5\205\1\0\21\205\4\0\2\55\1\56\2\0\1\55"+
    "\1\227\2\55\1\0\30\55\3\0\1\55\5\0\3\55"+
    "\1\0\1\56\21\55\4\0\2\55\1\56\2\0\1\55"+
    "\1\230\2\55\1\0\30\55\3\0\1\55\5\0\3\55"+
    "\1\0\1\56\21\55\4\0\2\55\1\56\2\0\1\55"+
    "\1\0\2\55\1\0\30\55\1\231\2\0\1\55\5\0"+
    "\3\55\1\0\1\56\21\55\4\0\2\55\1\56\2\0"+
    "\1\55\1\0\2\55\1\0\26\55\1\207\1\55\3\0"+
    "\1\55\5\0\3\55\1\0\1\56\21\55\4\0\2\55"+
    "\1\56\2\0\1\55\1\0\2\55\1\0\1\143\27\55"+
    "\3\0\1\55\5\0\3\55\1\0\1\56\21\55\4\0"+
    "\2\55\1\56\2\0\1\55\1\0\2\55\1\0\27\55"+
    "\1\206\3\0\1\55\5\0\3\55\1\0\1\56\21\55"+
    "\4\0\2\55\1\56\2\0\1\55\1\0\2\55\1\0"+
    "\1\55\1\143\26\55\3\0\1\55\5\0\3\55\1\0"+
    "\1\56\21\55\12\0\1\232\76\0\2\55\1\56\2\0"+
    "\1\55\1\0\2\55\1\0\12\55\1\142\15\55\3\0"+
    "\1\55\5\0\3\55\1\0\1\56\21\55\4\0\2\55"+
    "\1\56\2\0\1\55\1\0\2\55\1\0\21\55\1\145"+
    "\6\55\3\0\1\55\5\0\3\55\1\0\1\56\21\55"+
    "\50\0\1\233\67\0\1\234\75\0\1\235\66\0\1\236"+
    "\54\0\1\236\74\0\1\237\65\0\1\240\75\0\1\241"+
    "\65\0\1\236\41\0\1\242\12\0\1\236\33\0\1\243"+
    "\127\0\1\244\75\0\1\245\126\0\1\242\102\0\1\246"+
    "\74\0\1\247\75\0\1\250\124\0\1\251\71\0\1\252"+
    "\75\0\1\253\133\0\1\254\67\0\1\255\75\0\1\256"+
    "\116\0\1\257\74\0\1\260\75\0\1\260\103\0\1\261"+
    "\103\0\1\262\110\0\1\263\107\0\1\264\76\0\1\265"+
    "\137\0\1\266\76\0\1\167\37\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[10902];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\47\1\1\11\2\0\43\1\3\0\1\1"+
    "\1\0\22\1\3\0\14\1\1\0\1\11\2\0\1\1"+
    "\1\0\10\1\2\0\10\1\2\11\1\0\1\1\1\11"+
    "\3\1\5\0\1\1\6\0\1\1\15\0\1\11\6\0";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[182];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
 public String lexeme;



  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Lexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  Lexer(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 182) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public Tokens yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 14: 
          { lexeme=yytext(); return Pseudoinstruccion_Dup_Caracter;
          }
        case 20: break;
        case 8: 
          { lexeme=yytext(); return Constante_Numerica_H;
          }
        case 21: break;
        case 4: 
          { lexeme=yytext(); return Pseudoinstruccion_D_B;
          }
        case 22: break;
        case 6: 
          { return Linea;
          }
        case 23: break;
        case 19: 
          { lexeme=yytext(); return PseudoinstruccionInc;
          }
        case 24: break;
        case 5: 
          { lexeme=yytext(); return Pseudoinstruccion_D_W;
          }
        case 25: break;
        case 15: 
          { lexeme=yytext(); return Pseudoinstruccion_Dup_0;
          }
        case 26: break;
        case 7: 
          { lexeme=yytext(); return Simbolo;
          }
        case 27: break;
        case 18: 
          { lexeme=yytext(); return Pseudoinstruccion_Dup_H;
          }
        case 28: break;
        case 9: 
          { lexeme=yytext(); return Registro;
          }
        case 29: break;
        case 17: 
          { lexeme=yytext(); return Constante_Caracter_CD;
          }
        case 30: break;
        case 16: 
          { lexeme=yytext(); return Pseudoinstruccion;
          }
        case 31: break;
        case 2: 
          { lexeme=yytext(); return Constante_Numerica_D;
          }
        case 32: break;
        case 1: 
          { return ERROR;
          }
        case 33: break;
        case 12: 
          { lexeme=yytext(); return Constante_Caracter_CS;
          }
        case 34: break;
        case 10: 
          { lexeme=yytext(); return Sregistro;
          }
        case 35: break;
        case 3: 
          { /*Ignore*/
          }
        case 36: break;
        case 13: 
          { lexeme=yytext(); return ConstanteCadInc;
          }
        case 37: break;
        case 11: 
          { lexeme=yytext(); return Instruccion;
          }
        case 38: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            return null;
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
