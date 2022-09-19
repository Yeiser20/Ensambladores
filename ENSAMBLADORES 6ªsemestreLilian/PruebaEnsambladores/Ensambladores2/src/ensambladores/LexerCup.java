/* The following code was generated by JFlex 1.4.3 on 4/11/21 01:49 AM */

package ensambladores;
import java_cup.runtime.Symbol;

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 4/11/21 01:49 AM from the specification file
 * <tt>C:/Users/danie/OneDrive/Documentos/PruebaEnsambladores/Ensambladores2/src/ensambladores/LexerCup.flex</tt>
 */
class LexerCup implements java_cup.runtime.Scanner {

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
  private static final char [] ZZ_CMAP = {
     0,  0,  0,  0,  0,  0,  0,  0,  0,  1, 13,  0,  0,  1,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     3,  2,  0,  2,  2,  2,  0,  0,  2,  2,  2,  2,  7,  2,  2,  0, 
     4,  4,  4,  4,  4,  4,  4,  4,  4,  4,  2, 12,  0,  0,  0,  2, 
     0, 20, 44, 22, 16, 30, 48, 54, 46, 28, 50,  2, 23, 34, 42, 35, 
    40,  2, 29, 14, 15, 45, 36, 26, 49,  2, 52,  0,  0,  0,  0,  0, 
     0, 21, 10, 24, 19, 33,  5,  9,  6, 31, 51,  2, 25, 37, 43, 38, 
    41,  2, 32, 17, 18, 47, 39, 27, 11,  2, 53,  0,  8,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  2,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  2, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0
  };

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\2\1\1\2\3\1\1\2\30\1"+
    "\5\3\1\2\1\3\1\0\2\3\2\0\44\3\3\4"+
    "\2\0\2\4\2\0\1\5\1\6\1\3\1\5\4\3"+
    "\1\5\13\3\2\0\10\3";

  private static int [] zzUnpackAction() {
    int [] result = new int[121];
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
    "\0\0\0\67\0\156\0\245\0\334\0\u0113\0\u014a\0\u0181"+
    "\0\u01b8\0\u01ef\0\u0226\0\u025d\0\u0294\0\u02cb\0\u0302\0\u0339"+
    "\0\u0370\0\u03a7\0\u03de\0\u0415\0\u044c\0\u0483\0\u04ba\0\u04f1"+
    "\0\u0528\0\u055f\0\u0596\0\u05cd\0\u0604\0\u063b\0\u0672\0\u06a9"+
    "\0\u06e0\0\u0717\0\u074e\0\u0785\0\245\0\u07bc\0\u07f3\0\u082a"+
    "\0\u0861\0\u014a\0\u0898\0\u0181\0\u08cf\0\u0906\0\u093d\0\u0974"+
    "\0\u09ab\0\u09e2\0\u0a19\0\u0a50\0\u0a87\0\u0abe\0\u0af5\0\u0b2c"+
    "\0\u0b63\0\u0b9a\0\u0bd1\0\u0c08\0\u0c3f\0\u0c76\0\u0cad\0\u0ce4"+
    "\0\u0d1b\0\u0d52\0\u0d89\0\u0483\0\u0dc0\0\u0df7\0\u04f1\0\u0e2e"+
    "\0\u0e65\0\u0e9c\0\u0ed3\0\u0f0a\0\u0f41\0\u0f78\0\u0faf\0\u0fe6"+
    "\0\u101d\0\u1054\0\u0906\0\u108b\0\u10c2\0\u10f9\0\u1130\0\u1167"+
    "\0\u119e\0\u11d5\0\u120c\0\245\0\u1243\0\u127a\0\u0861\0\u12b1"+
    "\0\u12e8\0\u131f\0\u1356\0\u0181\0\u138d\0\u13c4\0\u13fb\0\u1432"+
    "\0\u1469\0\u14a0\0\u14d7\0\u150e\0\u1545\0\u157c\0\u15b3\0\u15ea"+
    "\0\u1621\0\u1658\0\u168f\0\u16c6\0\u16fd\0\u1734\0\u176b\0\u17a2"+
    "\0\u17d9";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[121];
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
    "\1\2\1\3\1\2\1\3\1\2\1\4\1\5\1\6"+
    "\2\7\1\10\1\11\1\12\1\0\1\13\1\4\1\14"+
    "\1\15\1\4\1\16\1\17\1\20\1\21\1\22\1\23"+
    "\1\24\2\4\1\25\1\26\1\10\1\27\1\30\1\10"+
    "\1\31\1\10\1\4\1\32\1\10\1\4\1\33\1\34"+
    "\1\35\1\36\1\10\1\4\1\37\2\4\1\40\1\41"+
    "\1\42\3\7\70\0\1\3\1\0\1\3\3\0\1\3"+
    "\61\0\2\43\1\44\3\45\1\0\1\43\2\45\1\43"+
    "\1\0\44\45\5\43\2\0\2\43\1\44\3\45\1\0"+
    "\1\43\2\45\1\43\1\0\4\45\1\46\37\45\5\43"+
    "\1\0\1\3\1\47\1\50\1\44\2\51\1\6\1\52"+
    "\1\53\2\54\1\47\1\0\1\54\2\51\1\54\2\51"+
    "\6\54\4\51\1\54\2\51\3\54\1\51\2\54\1\51"+
    "\5\54\4\51\1\54\5\53\2\0\2\55\1\0\2\55"+
    "\1\56\1\52\3\56\1\55\1\0\1\56\2\55\1\56"+
    "\2\55\6\56\4\55\1\56\2\55\3\56\1\55\2\56"+
    "\1\55\5\56\4\55\6\56\2\0\2\47\1\44\2\51"+
    "\1\54\1\52\1\53\2\54\1\47\1\0\1\54\2\51"+
    "\1\54\2\51\6\54\4\51\1\54\2\51\3\54\1\51"+
    "\2\54\1\51\5\54\4\51\1\54\5\53\2\0\2\47"+
    "\1\44\2\51\1\54\1\52\1\53\2\54\1\47\1\0"+
    "\1\54\2\51\1\54\2\51\5\54\1\57\4\51\1\54"+
    "\2\51\3\54\1\51\2\54\1\51\5\54\4\51\1\54"+
    "\5\53\15\12\1\0\51\12\2\0\2\47\1\44\2\51"+
    "\1\54\1\52\1\53\2\54\1\47\1\0\1\54\1\60"+
    "\1\51\1\54\2\51\2\54\1\61\3\54\4\51\1\54"+
    "\2\51\3\54\1\51\2\54\1\51\5\54\4\51\1\54"+
    "\5\53\2\0\2\43\1\44\3\45\1\0\1\43\2\45"+
    "\1\43\1\0\6\45\1\62\35\45\5\43\2\0\2\47"+
    "\1\44\2\51\1\54\1\52\1\53\2\54\1\47\1\0"+
    "\1\54\2\51\1\54\1\63\1\51\4\54\1\64\1\54"+
    "\4\51\1\54\2\51\3\54\1\51\2\54\1\51\5\54"+
    "\4\51\1\54\5\53\2\0\2\43\1\44\3\45\1\0"+
    "\1\43\2\45\1\43\1\0\7\45\1\65\34\45\5\43"+
    "\2\0\2\47\1\44\2\51\1\54\1\52\1\53\2\54"+
    "\1\47\1\0\1\54\2\51\1\54\2\51\1\66\5\54"+
    "\4\51\1\54\2\51\3\54\1\51\2\54\1\51\5\54"+
    "\4\51\1\54\5\53\2\0\2\47\1\44\2\51\1\54"+
    "\1\52\1\53\2\54\1\47\1\0\1\54\2\51\1\54"+
    "\2\51\1\54\1\67\4\54\4\51\1\54\2\51\3\54"+
    "\1\51\2\54\1\51\5\54\4\51\1\54\5\53\2\0"+
    "\2\47\1\44\2\51\1\54\1\52\1\53\2\54\1\47"+
    "\1\0\1\54\2\51\1\54\2\51\3\54\1\70\2\54"+
    "\1\71\3\51\1\54\2\51\1\54\1\72\1\54\1\51"+
    "\2\54\1\51\4\54\1\73\4\51\1\54\5\53\2\0"+
    "\2\47\1\44\2\51\1\54\1\52\1\53\2\54\1\47"+
    "\1\0\1\54\2\51\1\54\2\51\1\74\5\54\4\51"+
    "\1\54\2\51\2\54\1\75\1\51\2\54\1\51\5\54"+
    "\4\51\1\54\5\53\2\0\2\47\1\44\2\51\1\54"+
    "\1\52\1\53\1\76\1\54\1\47\1\0\1\54\2\51"+
    "\1\54\2\51\5\54\1\77\1\51\1\100\2\51\1\54"+
    "\2\51\3\54\1\51\1\101\1\54\1\51\5\54\4\51"+
    "\1\54\5\53\2\0\2\47\1\44\2\51\1\54\1\52"+
    "\1\53\2\54\1\47\1\0\1\54\2\51\1\54\2\51"+
    "\1\54\1\102\4\54\4\51\1\54\2\51\3\54\1\51"+
    "\1\54\1\103\1\51\5\54\4\51\1\54\5\53\2\0"+
    "\2\43\1\44\3\45\1\0\1\43\2\45\1\43\1\0"+
    "\17\45\1\104\14\45\1\105\7\45\5\43\2\0\2\43"+
    "\1\44\3\45\1\0\1\43\2\45\1\43\1\0\20\45"+
    "\1\106\23\45\5\43\2\0\2\43\1\44\3\45\1\0"+
    "\1\43\2\45\1\43\1\0\22\45\1\107\12\45\1\110"+
    "\6\45\5\43\2\0\2\43\1\44\3\45\1\0\1\43"+
    "\2\45\1\43\1\0\23\45\1\111\20\45\5\43\2\0"+
    "\2\47\1\44\2\51\1\54\1\52\1\53\2\54\1\47"+
    "\1\0\1\54\2\51\1\54\2\51\6\54\4\51\1\54"+
    "\2\51\2\54\1\112\1\51\2\54\1\51\5\54\4\51"+
    "\1\54\5\53\2\0\2\47\1\44\2\51\1\54\1\52"+
    "\1\53\2\54\1\47\1\0\1\54\2\51\1\54\2\51"+
    "\6\54\4\51\1\54\2\51\3\54\1\51\1\54\1\113"+
    "\1\51\5\54\4\51\1\54\5\53\2\0\2\47\1\44"+
    "\2\51\1\54\1\52\1\53\2\54\1\47\1\0\1\54"+
    "\2\51\1\54\2\51\6\54\4\51\1\54\2\51\2\54"+
    "\1\114\1\51\2\54\1\51\5\54\1\115\3\51\1\54"+
    "\5\53\2\0\2\47\1\44\2\51\1\54\1\52\1\53"+
    "\2\54\1\47\1\0\1\54\2\51\1\54\2\51\6\54"+
    "\4\51\1\54\2\51\3\54\1\51\1\54\1\116\1\51"+
    "\5\54\2\51\1\117\1\51\1\54\5\53\2\0\2\47"+
    "\1\44\2\51\1\54\1\52\1\53\2\54\1\47\1\0"+
    "\1\54\2\51\1\54\2\51\6\54\4\51\1\54\2\51"+
    "\2\54\1\120\1\51\2\54\1\51\5\54\4\51\1\54"+
    "\5\53\2\0\2\47\1\44\2\51\1\54\1\52\1\53"+
    "\2\54\1\47\1\0\1\54\2\51\1\54\2\51\6\54"+
    "\4\51\1\54\2\51\3\54\1\51\1\54\1\121\1\51"+
    "\5\54\4\51\1\54\5\53\2\0\2\43\1\44\3\45"+
    "\1\0\1\43\2\45\1\43\1\0\11\45\1\106\32\45"+
    "\5\43\2\0\2\47\1\44\2\51\1\54\1\52\1\53"+
    "\2\54\1\47\1\0\1\54\2\51\1\54\2\51\3\54"+
    "\1\122\2\54\4\51\1\54\2\51\3\54\1\51\2\54"+
    "\1\51\5\54\4\51\1\54\5\53\2\0\2\55\1\0"+
    "\2\55\1\56\1\52\3\56\1\55\1\0\1\123\2\55"+
    "\1\56\2\55\1\124\1\56\1\125\1\124\2\56\4\55"+
    "\1\123\2\55\1\56\1\126\1\123\1\55\2\56\1\55"+
    "\1\123\1\56\1\127\1\56\1\124\4\55\3\56\1\123"+
    "\1\56\1\124\2\0\2\55\1\0\2\55\1\56\1\52"+
    "\2\130\1\56\1\55\1\0\1\56\2\55\1\123\2\55"+
    "\1\56\1\130\2\56\1\131\1\130\4\55\1\56\2\55"+
    "\1\123\2\56\1\55\1\132\1\123\1\55\1\56\1\123"+
    "\1\56\1\133\1\56\4\55\4\56\1\123\1\56\2\0"+
    "\2\43\1\0\3\43\1\0\4\43\1\0\51\43\4\0"+
    "\1\44\64\0\2\43\1\44\3\45\1\0\1\43\2\45"+
    "\1\43\1\0\13\45\1\134\30\45\5\43\2\0\2\47"+
    "\1\135\3\47\1\0\4\47\1\0\51\47\1\0\1\3"+
    "\1\47\1\50\1\135\2\47\1\50\1\0\4\47\1\0"+
    "\51\47\2\0\2\47\1\44\3\51\1\0\1\47\2\51"+
    "\1\47\1\0\44\51\5\47\2\0\2\47\1\135\2\47"+
    "\1\53\1\52\3\53\1\47\1\0\1\53\2\47\1\53"+
    "\2\47\6\53\4\47\1\53\2\47\3\53\1\47\2\53"+
    "\1\47\5\53\4\47\6\53\2\0\2\55\1\135\3\55"+
    "\1\0\4\55\1\0\51\55\2\0\2\55\1\135\2\55"+
    "\1\56\1\52\3\56\1\55\1\0\1\56\2\55\1\56"+
    "\2\55\6\56\4\55\1\56\2\55\3\56\1\55\2\56"+
    "\1\55\5\56\4\55\6\56\2\0\2\47\1\44\2\51"+
    "\1\54\1\52\1\53\2\54\1\47\1\0\1\54\2\51"+
    "\1\54\2\51\1\54\1\136\4\54\4\51\1\54\2\51"+
    "\3\54\1\51\2\54\1\51\5\54\4\51\1\54\5\53"+
    "\2\0\2\47\1\44\3\51\1\0\1\47\2\51\1\47"+
    "\1\0\2\51\1\137\5\51\1\137\5\51\1\137\6\51"+
    "\1\140\16\51\5\47\2\0\2\47\1\44\2\51\1\54"+
    "\1\52\1\53\2\54\1\47\1\0\1\54\2\51\1\54"+
    "\2\51\1\141\5\54\4\51\1\54\2\51\3\54\1\51"+
    "\2\54\1\51\5\54\4\51\1\54\5\53\2\0\2\43"+
    "\1\44\3\45\1\0\1\43\2\45\1\43\1\0\1\134"+
    "\5\45\1\134\35\45\5\43\2\0\2\47\1\44\3\51"+
    "\1\0\1\47\2\51\1\47\1\0\5\51\1\137\4\51"+
    "\1\137\6\51\1\137\6\51\1\142\13\51\5\47\2\0"+
    "\2\47\1\44\2\51\1\54\1\52\1\53\2\54\1\47"+
    "\1\0\1\54\2\51\1\54\2\51\1\54\1\143\4\54"+
    "\4\51\1\54\2\51\3\54\1\51\2\54\1\51\5\54"+
    "\4\51\1\54\5\53\2\0\2\43\1\44\3\45\1\0"+
    "\1\43\2\45\1\43\1\0\3\45\1\134\3\45\1\134"+
    "\34\45\5\43\2\0\2\47\1\44\2\51\1\54\1\52"+
    "\1\53\2\54\1\47\1\0\1\144\1\51\1\137\1\54"+
    "\2\51\1\144\5\54\4\51\1\54\2\51\1\54\1\144"+
    "\1\54\1\51\2\54\1\51\5\54\4\51\1\54\5\53"+
    "\2\0\2\47\1\44\2\51\1\54\1\52\1\53\2\54"+
    "\1\47\1\0\1\54\2\51\1\144\1\51\1\137\1\54"+
    "\1\144\4\54\4\51\1\54\2\51\3\54\1\51\1\144"+
    "\1\54\1\51\5\54\4\51\1\54\5\53\2\0\2\47"+
    "\1\44\2\51\1\54\1\52\1\53\2\54\1\47\1\0"+
    "\1\54\1\51\1\137\1\54\2\51\2\54\1\144\3\54"+
    "\2\51\1\137\1\51\1\54\2\51\3\54\1\51\2\54"+
    "\1\51\5\54\4\51\1\54\5\53\2\0\2\47\1\44"+
    "\3\51\1\0\1\47\2\51\1\47\1\0\2\51\1\137"+
    "\41\51\5\47\2\0\2\47\1\44\2\51\1\54\1\52"+
    "\1\53\2\54\1\47\1\0\1\54\2\51\1\54\2\51"+
    "\2\54\1\144\3\54\4\51\1\54\2\51\3\54\1\51"+
    "\2\54\1\51\1\145\4\54\4\51\1\54\5\53\2\0"+
    "\2\47\1\44\2\51\1\54\1\52\1\53\2\54\1\47"+
    "\1\0\1\54\2\51\1\54\2\51\6\54\1\137\3\51"+
    "\1\54\2\51\3\54\1\51\2\54\1\51\5\54\4\51"+
    "\1\54\5\53\2\0\2\47\1\44\2\51\1\54\1\52"+
    "\1\53\2\54\1\47\1\0\1\54\2\51\1\54\2\51"+
    "\6\54\4\51\1\54\2\51\3\54\1\51\2\54\1\51"+
    "\5\54\1\51\1\146\2\51\1\54\5\53\2\0\2\47"+
    "\1\44\2\51\1\54\1\52\1\53\2\54\1\47\1\0"+
    "\1\54\1\51\1\140\1\54\2\51\6\54\4\51\1\54"+
    "\2\51\3\54\1\51\2\54\1\51\5\54\4\51\1\54"+
    "\5\53\2\0\2\47\1\44\2\51\1\54\1\52\1\53"+
    "\2\54\1\47\1\0\1\54\2\51\1\54\2\51\6\54"+
    "\1\51\1\137\2\51\1\54\2\51\3\54\1\51\2\54"+
    "\1\51\5\54\4\51\1\54\5\53\2\0\2\47\1\44"+
    "\2\51\1\54\1\52\1\53\2\54\1\47\1\0\1\54"+
    "\2\51\1\54\1\51\1\137\4\54\1\144\1\54\4\51"+
    "\1\54\1\137\1\51\3\54\1\51\2\54\1\51\5\54"+
    "\4\51\1\54\5\53\2\0\2\47\1\44\3\51\1\0"+
    "\1\47\2\51\1\47\1\0\5\51\1\137\36\51\5\47"+
    "\2\0\2\47\1\44\2\51\1\54\1\52\1\53\2\54"+
    "\1\47\1\0\1\54\2\51\1\54\2\51\4\54\1\144"+
    "\1\54\4\51\1\54\2\51\3\54\1\51\2\54\1\51"+
    "\1\54\1\147\3\54\4\51\1\54\5\53\2\0\2\47"+
    "\1\44\1\51\1\150\1\54\1\52\1\53\2\54\1\47"+
    "\1\0\1\54\2\51\1\54\2\51\6\54\4\51\1\54"+
    "\2\51\3\54\1\51\2\54\1\51\5\54\4\51\1\54"+
    "\5\53\2\0\2\47\1\44\2\51\1\54\1\52\1\53"+
    "\2\54\1\47\1\0\1\54\2\51\1\54\1\51\1\142"+
    "\6\54\4\51\1\54\2\51\3\54\1\51\2\54\1\51"+
    "\5\54\4\51\1\54\5\53\2\0\2\43\1\44\3\45"+
    "\1\0\1\43\2\45\1\43\1\0\1\45\1\151\42\45"+
    "\5\43\2\0\2\43\1\44\3\45\1\0\1\43\2\45"+
    "\1\43\1\0\1\45\1\134\42\45\5\43\2\0\2\43"+
    "\1\44\3\45\1\0\1\43\2\45\1\43\1\0\4\45"+
    "\1\152\37\45\5\43\2\0\2\43\1\44\3\45\1\0"+
    "\1\43\2\45\1\43\1\0\4\45\1\134\37\45\5\43"+
    "\2\0\2\47\1\44\2\51\1\54\1\52\1\53\2\54"+
    "\1\47\1\0\1\54\2\51\1\54\2\51\6\54\4\51"+
    "\1\54\2\51\3\54\1\140\2\54\1\51\5\54\4\51"+
    "\1\54\5\53\2\0\2\47\1\44\2\51\1\54\1\52"+
    "\1\53\2\54\1\47\1\0\1\54\2\51\1\54\2\51"+
    "\6\54\4\51\1\54\2\51\3\54\1\51\2\54\1\142"+
    "\5\54\4\51\1\54\5\53\2\0\2\47\1\44\2\51"+
    "\1\54\1\52\1\53\2\54\1\47\1\0\1\54\2\51"+
    "\1\54\2\51\6\54\4\51\1\54\2\51\3\54\1\51"+
    "\2\54\1\51\1\153\4\54\4\51\1\54\5\53\2\0"+
    "\2\47\1\44\3\51\1\0\1\47\2\51\1\47\1\0"+
    "\1\154\43\51\5\47\2\0\2\47\1\44\2\51\1\54"+
    "\1\52\1\53\2\54\1\47\1\0\1\54\2\51\1\54"+
    "\2\51\6\54\4\51\1\54\2\51\3\54\1\51\2\54"+
    "\1\51\1\54\1\155\3\54\4\51\1\54\5\53\2\0"+
    "\2\47\1\44\3\51\1\0\1\47\2\51\1\47\1\0"+
    "\3\51\1\156\40\51\5\47\2\0\2\47\1\44\2\51"+
    "\1\54\1\52\1\53\2\54\1\47\1\0\1\54\2\51"+
    "\1\54\2\51\6\54\4\51\1\54\2\51\3\54\1\51"+
    "\2\54\1\51\1\144\4\54\4\51\1\54\5\53\2\0"+
    "\2\47\1\44\2\51\1\54\1\52\1\53\2\54\1\47"+
    "\1\0\1\54\2\51\1\54\2\51\6\54\4\51\1\54"+
    "\2\51\3\54\1\51\2\54\1\51\1\54\1\144\3\54"+
    "\4\51\1\54\5\53\2\0\2\47\1\44\2\51\1\54"+
    "\1\52\1\53\2\54\1\47\1\0\1\54\2\51\1\54"+
    "\2\51\1\157\5\54\4\51\1\54\2\51\3\54\1\51"+
    "\2\54\1\51\5\54\4\51\1\54\5\53\2\0\2\55"+
    "\1\135\2\55\1\56\1\52\3\56\1\55\1\0\1\56"+
    "\2\55\1\56\2\55\6\56\4\55\1\123\2\55\3\56"+
    "\1\55\2\56\1\55\5\56\4\55\6\56\2\0\2\55"+
    "\1\135\2\55\1\56\1\52\3\56\1\55\1\0\1\56"+
    "\2\55\1\56\2\55\6\56\4\55\1\56\2\55\3\56"+
    "\1\55\2\56\1\55\5\56\4\55\1\160\5\56\2\0"+
    "\2\55\1\135\2\55\1\56\1\52\3\56\1\55\1\0"+
    "\1\56\2\55\1\56\2\55\6\56\4\55\1\56\2\55"+
    "\3\56\1\55\2\56\1\55\1\123\4\56\4\55\6\56"+
    "\2\0\2\55\1\135\2\55\1\56\1\52\3\56\1\55"+
    "\1\0\1\123\2\55\1\56\2\55\1\124\1\56\1\123"+
    "\1\124\2\56\4\55\1\123\2\55\2\56\1\123\1\55"+
    "\2\56\1\55\1\123\3\56\1\124\4\55\3\56\1\123"+
    "\1\56\1\123\2\0\2\55\1\135\2\55\1\56\1\52"+
    "\3\56\1\55\1\0\1\56\2\55\1\56\2\55\6\56"+
    "\4\55\1\56\2\55\1\123\2\56\1\55\2\56\1\55"+
    "\5\56\4\55\6\56\2\0\2\55\1\135\2\55\1\56"+
    "\1\52\2\56\1\161\1\55\1\0\1\56\2\55\1\56"+
    "\2\55\6\56\4\55\1\56\2\55\3\56\1\55\2\56"+
    "\1\55\5\56\4\55\6\56\2\0\2\55\1\135\2\55"+
    "\1\56\1\52\3\56\1\55\1\0\1\56\2\55\1\56"+
    "\2\55\6\56\4\55\1\56\2\55\3\56\1\55\2\56"+
    "\1\55\1\56\1\123\3\56\4\55\6\56\2\0\2\55"+
    "\1\135\2\55\1\56\1\52\1\123\1\130\1\56\1\55"+
    "\1\0\1\56\2\55\1\123\2\55\1\56\1\130\2\56"+
    "\1\123\1\130\4\55\1\56\2\55\1\123\2\56\1\55"+
    "\1\56\1\123\1\55\1\56\1\123\3\56\4\55\4\56"+
    "\1\123\1\56\4\0\1\135\64\0\2\47\1\44\2\51"+
    "\1\54\1\52\1\53\2\54\1\47\1\0\1\54\2\51"+
    "\1\54\1\162\1\51\6\54\4\51\1\54\2\51\3\54"+
    "\1\51\2\54\1\51\5\54\4\51\1\54\5\53\2\0"+
    "\2\47\1\44\3\51\1\0\1\47\2\51\1\47\1\0"+
    "\1\163\43\51\5\47\2\0\2\47\1\44\2\51\1\54"+
    "\1\52\1\53\2\54\1\47\1\0\1\73\2\51\1\54"+
    "\2\51\6\54\4\51\1\54\2\51\3\54\1\51\2\54"+
    "\1\51\5\54\4\51\1\54\5\53\2\0\2\47\1\44"+
    "\3\51\1\0\1\47\2\51\1\47\1\0\3\51\1\164"+
    "\40\51\5\47\2\0\2\47\1\44\2\51\1\54\1\52"+
    "\1\53\2\54\1\47\1\0\1\54\2\51\1\76\2\51"+
    "\6\54\4\51\1\54\2\51\3\54\1\51\2\54\1\51"+
    "\5\54\4\51\1\54\5\53\2\0\2\47\1\44\2\51"+
    "\1\54\1\52\1\53\2\54\1\47\1\0\1\165\2\51"+
    "\1\54\2\51\6\54\4\51\1\54\2\51\3\54\1\51"+
    "\2\54\1\51\5\54\4\51\1\54\5\53\2\0\2\47"+
    "\1\44\3\51\1\0\1\47\2\51\1\47\1\0\42\51"+
    "\1\137\1\51\5\47\2\0\2\47\1\44\2\51\1\54"+
    "\1\52\1\53\2\54\1\47\1\0\1\54\2\51\1\166"+
    "\2\51\6\54\4\51\1\54\2\51\3\54\1\51\2\54"+
    "\1\51\5\54\4\51\1\54\5\53\2\0\2\47\1\44"+
    "\1\137\2\51\1\0\1\47\2\51\1\47\1\0\44\51"+
    "\5\47\2\0\2\43\1\44\3\45\1\0\1\43\2\45"+
    "\1\43\1\0\25\45\1\134\16\45\5\43\2\0\2\43"+
    "\1\44\3\45\1\0\1\43\2\45\1\43\1\0\30\45"+
    "\1\134\13\45\5\43\2\0\2\47\1\44\2\51\1\54"+
    "\1\52\1\53\2\54\1\47\1\0\1\54\2\51\1\54"+
    "\2\51\1\144\5\54\4\51\1\54\2\51\3\54\1\51"+
    "\2\54\1\51\5\54\3\51\1\137\1\54\5\53\2\0"+
    "\2\47\1\44\3\51\1\0\1\47\2\51\1\47\1\0"+
    "\40\51\1\167\3\51\5\47\2\0\2\47\1\44\1\137"+
    "\1\51\1\54\1\52\1\53\2\54\1\47\1\0\1\54"+
    "\2\51\1\54\2\51\1\54\1\144\4\54\4\51\1\54"+
    "\2\51\3\54\1\51\2\54\1\51\5\54\4\51\1\54"+
    "\5\53\2\0\2\47\1\44\1\51\1\170\1\51\1\0"+
    "\1\47\2\51\1\47\1\0\44\51\5\47\2\0\2\47"+
    "\1\44\2\51\1\54\1\52\1\53\2\54\1\47\1\0"+
    "\1\54\1\171\1\51\1\54\2\51\6\54\4\51\1\54"+
    "\2\51\3\54\1\51\2\54\1\51\5\54\4\51\1\54"+
    "\5\53\2\0\2\55\1\135\2\55\1\56\1\52\3\56"+
    "\1\55\1\0\1\56\2\55\1\56\2\55\6\56\4\55"+
    "\1\56\2\55\3\56\1\55\2\56\1\55\5\56\4\55"+
    "\3\56\1\123\2\56\2\0\2\55\1\135\2\55\1\56"+
    "\1\52\3\56\1\55\1\0\1\56\2\55\1\56\2\55"+
    "\6\56\4\55\1\56\2\55\3\56\1\55\2\56\1\55"+
    "\5\56\4\55\4\56\1\123\1\56\2\0\2\47\1\44"+
    "\3\51\1\0\1\47\1\137\1\51\1\47\1\0\44\51"+
    "\5\47\2\0\2\47\1\44\3\51\1\0\1\47\2\51"+
    "\1\47\1\0\14\51\1\137\21\51\1\137\5\51\5\47"+
    "\2\0\2\47\1\44\3\51\1\0\1\47\1\137\1\51"+
    "\1\47\1\0\15\51\1\137\26\51\5\47\2\0\2\47"+
    "\1\44\2\51\1\54\1\52\1\53\2\54\1\47\1\0"+
    "\1\54\2\51\1\54\2\51\6\54\1\137\3\51\1\54"+
    "\2\51\3\54\1\51\2\54\1\51\4\54\1\144\4\51"+
    "\1\54\5\53\2\0\2\47\1\44\2\51\1\54\1\52"+
    "\1\53\1\144\1\54\1\47\1\0\1\54\2\51\1\54"+
    "\2\51\6\54\1\51\1\137\2\51\1\54\2\51\3\54"+
    "\1\51\2\54\1\51\5\54\4\51\1\54\5\53\2\0"+
    "\2\47\1\44\3\51\1\0\1\47\2\51\1\47\1\0"+
    "\6\51\1\137\33\51\1\137\1\51\5\47\2\0\2\47"+
    "\1\44\1\137\2\51\1\0\1\47\2\51\1\47\1\0"+
    "\7\51\1\137\34\51\5\47\2\0\2\47\1\44\3\51"+
    "\1\0\1\47\2\51\1\47\1\0\36\51\1\137\5\51"+
    "\5\47";

  private static int [] zzUnpackTrans() {
    int [] result = new int[6160];
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
    "\1\0\1\11\47\1\1\0\2\1\2\0\47\1\2\0"+
    "\2\1\2\0\24\1\2\0\10\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[121];
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
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  LexerCup(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  LexerCup(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
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
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
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

      yychar+= zzMarkedPosL-zzStartRead;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          zzR = false;
          break;
        case '\r':
          yyline++;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
          }
          break;
        default:
          zzR = false;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
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
        case 2: 
          { /*Ignore*/
          }
        case 7: break;
        case 6: 
          { return new Symbol(sym.IIE, yychar, yyline, yytext());
          }
        case 8: break;
        case 5: 
          { return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());
          }
        case 9: break;
        case 4: 
          { return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());
          }
        case 10: break;
        case 1: 
          { return new Symbol(sym.ERROR, yychar, yyline, yytext());
          }
        case 11: break;
        case 3: 
          { return new Symbol(sym.IIO, yychar, yyline, yytext());
          }
        case 12: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return new java_cup.runtime.Symbol(sym.EOF); }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
