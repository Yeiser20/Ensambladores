/* The following code was generated by JFlex 1.4.3 on 9/11/21 11:44 PM */

package analisadorlexico;

import static analisadorlexico.Tokens.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 9/11/21 11:44 PM from the specification file
 * <tt>C:/Users/danie/OneDrive/Documentos/5ª SEMESTRE FACULTAD/ENSAMBLADORES/AnalisadorLexico/src/analisadorlexico/Fase3.flex</tt>
 */
class Fase3 {

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
    "\11\0\1\5\1\50\2\0\1\5\22\0\1\11\1\7\1\61\3\7"+
    "\1\0\1\53\1\52\1\54\2\7\1\1\2\7\1\0\1\57\1\60"+
    "\1\15\1\16\1\56\1\15\1\16\3\4\1\51\1\47\3\0\1\7"+
    "\1\0\1\34\1\13\1\23\1\21\1\22\1\10\1\37\1\55\1\20"+
    "\1\10\1\46\1\20\1\40\1\41\1\44\1\17\1\24\1\10\1\36"+
    "\1\35\1\3\1\10\1\14\1\20\2\10\4\0\1\2\1\0\1\26"+
    "\1\13\1\42\1\12\1\25\1\10\1\31\1\6\1\20\1\10\1\45"+
    "\1\20\1\32\1\33\1\43\1\17\1\24\1\10\1\30\1\27\1\3"+
    "\1\10\1\14\1\20\2\10\46\0\1\7\35\0\1\7\21\0\1\0"+
    "\37\0\1\0\uff0e\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\1\1\3\1\1\1\2\3\1"+
    "\1\3\1\1\1\3\3\1\1\2\1\4\1\3\1\2"+
    "\3\0\1\3\16\0\1\5\13\0\2\3\10\0\1\6"+
    "\15\0\1\7\1\10\1\0\1\10\1\0\1\10\1\0"+
    "\1\10\1\0\2\10\1\0\3\10\5\0\1\7\3\0"+
    "\1\10\2\0\2\10\2\0\2\10\3\0\1\10\2\0"+
    "\2\10\3\0\1\7\2\0\1\7\1\10\3\0\1\10"+
    "\5\0\1\7\2\0\1\7\1\6";

  private static int [] zzUnpackAction() {
    int [] result = new int[137];
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
    "\0\0\0\62\0\144\0\226\0\310\0\372\0\u012c\0\u015e"+
    "\0\u0190\0\u01c2\0\u01f4\0\u0226\0\u0258\0\u028a\0\u02bc\0\u02ee"+
    "\0\u0320\0\62\0\u0352\0\u0384\0\u03b6\0\u03e8\0\u041a\0\u044c"+
    "\0\u047e\0\372\0\u04b0\0\u04e2\0\u0514\0\u0546\0\u0578\0\u05aa"+
    "\0\u05dc\0\u060e\0\u0640\0\u0672\0\u06a4\0\u06d6\0\62\0\u0708"+
    "\0\u073a\0\u076c\0\u079e\0\u07d0\0\u0802\0\u0834\0\u0866\0\u0898"+
    "\0\u08ca\0\u08fc\0\u092e\0\u0960\0\u0992\0\u09c4\0\u09f6\0\u0a28"+
    "\0\u0a5a\0\u0a8c\0\u0abe\0\u0af0\0\u03e8\0\u0b22\0\u0b54\0\u0b86"+
    "\0\u0bb8\0\u0bea\0\u0c1c\0\u0c4e\0\u0c80\0\u0cb2\0\u0ce4\0\u0d16"+
    "\0\u0d48\0\u0d7a\0\u0dac\0\u0dde\0\u0e10\0\u0e42\0\u0e74\0\u0ea6"+
    "\0\u0ed8\0\u0f0a\0\u0f3c\0\u0f6e\0\u0fa0\0\u0fd2\0\u1004\0\u1036"+
    "\0\u1068\0\u109a\0\u10cc\0\u10fe\0\u1130\0\u1162\0\u1194\0\u11c6"+
    "\0\u11f8\0\u122a\0\u125c\0\u128e\0\u12c0\0\u12f2\0\u1324\0\u1356"+
    "\0\u1388\0\u10fe\0\62\0\u13ba\0\u13ec\0\u141e\0\u1450\0\u1482"+
    "\0\u14b4\0\u14e6\0\u1518\0\u154a\0\u157c\0\u15ae\0\u15e0\0\u1612"+
    "\0\u1644\0\u0fa0\0\u15e0\0\u1676\0\u16a8\0\u16da\0\u170c\0\u173e"+
    "\0\u1770\0\u17a2\0\u17d4\0\u1806\0\u0e42\0\u1838\0\u186a\0\62"+
    "\0\62";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[137];
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
    "\1\2\1\3\1\4\1\5\1\6\1\7\1\4\1\2"+
    "\1\4\1\7\1\10\1\4\1\11\2\6\2\4\1\12"+
    "\1\13\1\14\1\5\1\15\2\4\1\16\5\4\1\17"+
    "\3\4\1\20\4\4\1\21\1\22\4\2\1\4\3\6"+
    "\1\2\63\0\1\3\1\0\1\23\1\0\1\7\3\0"+
    "\1\24\1\25\1\0\1\25\4\0\1\25\1\23\1\0"+
    "\2\23\36\0\2\26\1\27\1\0\1\26\1\0\1\26"+
    "\1\0\3\26\2\27\30\26\6\0\1\26\3\27\2\0"+
    "\1\23\1\26\1\30\1\27\1\0\1\26\1\0\1\26"+
    "\1\0\3\26\2\27\3\26\1\30\1\26\2\30\21\26"+
    "\6\0\1\26\3\27\3\0\2\31\1\32\1\0\1\31"+
    "\1\0\1\31\1\0\3\31\2\32\30\31\6\0\1\31"+
    "\3\32\2\0\1\7\3\0\1\7\3\0\1\7\51\0"+
    "\1\25\2\26\1\27\1\0\1\26\1\0\1\26\1\33"+
    "\1\34\1\26\1\34\2\27\2\26\1\34\4\26\1\35"+
    "\20\26\6\0\1\26\3\27\2\0\1\25\2\26\1\27"+
    "\1\0\1\26\1\0\1\26\1\33\1\34\1\26\1\34"+
    "\2\27\2\26\1\34\25\26\6\0\1\26\3\27\2\0"+
    "\1\25\2\26\1\27\1\0\1\26\1\0\1\26\1\33"+
    "\1\34\1\26\1\34\2\27\2\26\1\34\12\26\1\36"+
    "\12\26\6\0\1\26\3\27\2\0\1\23\1\26\1\30"+
    "\1\27\1\0\1\26\1\0\1\26\1\0\3\26\2\27"+
    "\3\26\1\30\1\26\2\30\13\26\1\37\5\26\6\0"+
    "\1\26\3\27\3\0\2\26\1\27\1\0\1\26\1\0"+
    "\1\26\1\0\3\26\2\27\25\26\1\40\2\26\6\0"+
    "\1\26\3\27\2\0\1\23\1\26\1\30\1\27\1\0"+
    "\1\26\1\0\1\26\1\0\3\26\2\27\3\26\1\30"+
    "\1\26\2\30\5\26\1\41\13\26\6\0\1\26\3\27"+
    "\3\0\2\26\1\27\1\0\1\26\1\0\1\26\1\0"+
    "\3\26\2\27\10\26\1\42\17\26\6\0\1\26\3\27"+
    "\3\0\2\26\1\27\1\0\1\26\1\0\1\26\1\0"+
    "\3\26\2\27\16\26\1\43\11\26\6\0\1\26\3\27"+
    "\3\0\2\26\1\27\1\0\1\26\1\0\1\26\1\0"+
    "\3\26\2\27\24\26\1\44\3\26\6\0\1\26\3\27"+
    "\1\0\50\21\1\0\11\21\1\0\1\23\1\0\1\23"+
    "\16\0\1\23\1\0\2\23\35\0\1\7\1\45\2\0"+
    "\1\7\3\0\1\7\3\0\2\45\37\0\3\45\2\0"+
    "\1\25\7\0\1\33\1\25\1\0\1\25\4\0\1\25"+
    "\42\0\2\26\1\27\1\0\1\26\1\0\1\26\1\46"+
    "\3\26\2\27\30\26\2\0\1\47\3\0\1\26\3\27"+
    "\5\0\1\27\4\0\1\46\3\0\2\27\32\0\1\47"+
    "\4\0\3\27\2\0\1\23\1\26\1\30\1\27\1\0"+
    "\1\26\1\0\1\26\1\46\3\26\2\27\3\26\1\30"+
    "\1\26\2\30\21\26\2\0\1\47\3\0\1\26\3\27"+
    "\3\0\2\31\2\0\1\31\1\0\1\31\1\50\3\31"+
    "\2\0\30\31\2\0\1\47\3\0\1\31\6\0\1\45"+
    "\12\0\2\45\37\0\3\45\2\0\1\25\2\26\1\27"+
    "\1\0\1\26\1\0\1\26\1\51\1\34\1\26\1\34"+
    "\2\27\2\26\1\34\25\26\2\0\1\47\3\0\1\26"+
    "\3\27\3\0\2\26\1\27\1\0\1\26\1\0\1\26"+
    "\1\46\3\26\2\27\10\26\1\52\17\26\2\0\1\47"+
    "\3\0\1\26\3\27\3\0\2\26\1\27\1\0\1\26"+
    "\1\0\1\26\1\46\3\26\2\27\16\26\1\53\11\26"+
    "\2\0\1\47\3\0\1\26\3\27\3\0\2\26\1\27"+
    "\1\0\1\26\1\0\1\26\1\46\3\26\2\27\2\26"+
    "\1\54\25\26\2\0\1\47\3\0\1\26\3\27\3\0"+
    "\2\26\1\27\1\0\1\26\1\0\1\26\1\46\3\26"+
    "\2\27\2\26\1\55\25\26\2\0\1\47\3\0\1\26"+
    "\3\27\3\0\2\26\1\27\1\0\1\26\1\0\1\26"+
    "\1\46\1\56\2\26\2\27\30\26\2\0\1\47\3\0"+
    "\1\26\3\27\3\0\2\26\1\27\1\0\1\26\1\0"+
    "\1\26\1\46\3\26\2\27\7\26\1\57\20\26\2\0"+
    "\1\47\3\0\1\26\3\27\3\0\2\26\1\27\1\0"+
    "\1\26\1\0\1\26\1\46\3\26\2\27\15\26\1\60"+
    "\12\26\2\0\1\47\3\0\1\26\3\27\3\0\2\26"+
    "\1\27\1\0\1\26\1\0\1\26\1\46\1\61\2\26"+
    "\2\27\30\26\2\0\1\47\3\0\1\26\3\27\3\0"+
    "\1\45\6\0\1\62\3\0\2\45\37\0\3\45\2\0"+
    "\1\63\1\0\1\64\6\0\1\65\1\66\1\67\4\0"+
    "\1\65\1\64\1\0\2\64\35\0\1\70\10\0\1\70"+
    "\1\71\1\72\4\0\1\70\41\0\1\63\1\45\1\64"+
    "\6\0\1\65\1\66\1\67\2\45\2\0\1\65\1\64"+
    "\1\0\2\64\30\0\3\45\3\0\2\26\1\27\1\0"+
    "\1\26\1\0\1\26\1\46\3\26\2\27\7\26\1\73"+
    "\20\26\2\0\1\47\3\0\1\26\3\27\3\0\2\26"+
    "\1\27\1\0\1\26\1\0\1\26\1\46\3\26\2\27"+
    "\15\26\1\74\12\26\2\0\1\47\3\0\1\26\3\27"+
    "\3\0\2\26\1\27\1\0\1\26\1\0\1\26\1\46"+
    "\3\26\2\27\17\26\1\75\10\26\2\0\1\47\3\0"+
    "\1\26\3\27\3\0\2\26\1\27\1\0\1\26\1\0"+
    "\1\26\1\46\3\26\2\27\3\26\1\74\24\26\2\0"+
    "\1\47\3\0\1\26\3\27\3\0\2\26\1\27\1\0"+
    "\1\26\1\0\1\26\1\46\3\26\2\27\11\26\1\75"+
    "\16\26\2\0\1\47\3\0\1\26\3\27\3\0\2\26"+
    "\1\27\1\0\1\26\1\0\1\26\1\46\3\26\2\27"+
    "\23\26\1\76\4\26\2\0\1\47\3\0\1\26\3\27"+
    "\3\0\2\26\1\27\1\0\1\26\1\0\1\26\1\46"+
    "\3\26\2\27\4\26\1\77\23\26\2\0\1\47\3\0"+
    "\1\26\3\27\3\0\2\26\1\27\1\0\1\26\1\0"+
    "\1\26\1\46\3\26\2\27\6\26\1\73\21\26\2\0"+
    "\1\47\3\0\1\26\3\27\2\0\1\100\1\0\1\100"+
    "\6\0\1\100\4\0\1\100\1\0\1\100\41\0\1\63"+
    "\1\0\1\64\5\0\1\101\1\65\1\66\1\67\4\0"+
    "\1\65\1\64\1\0\2\64\35\0\1\64\1\0\1\64"+
    "\5\0\1\102\10\0\1\64\1\0\2\64\35\0\1\65"+
    "\7\0\1\103\1\65\1\66\1\67\4\0\1\65\41\0"+
    "\1\66\7\0\1\104\2\66\5\0\1\66\41\0\1\67"+
    "\7\0\1\105\1\67\1\0\1\67\4\0\1\67\41\0"+
    "\1\70\7\0\1\106\1\70\1\71\1\72\4\0\1\70"+
    "\41\0\1\71\7\0\1\107\2\71\5\0\1\71\41\0"+
    "\1\72\7\0\1\106\1\72\1\0\1\72\4\0\1\72"+
    "\42\0\2\26\1\27\1\0\1\26\1\0\1\26\1\110"+
    "\3\26\2\27\30\26\2\0\1\47\3\0\1\26\3\27"+
    "\3\0\2\26\1\27\1\0\1\26\1\0\1\26\1\111"+
    "\3\26\2\27\30\26\2\0\1\47\3\0\1\26\3\27"+
    "\3\0\2\26\1\27\1\0\1\26\1\0\1\26\1\46"+
    "\3\26\2\27\26\26\1\73\1\26\2\0\1\47\3\0"+
    "\1\26\3\27\3\0\2\26\1\27\1\0\1\26\1\0"+
    "\1\26\1\46\3\26\2\27\27\26\1\74\2\0\1\47"+
    "\3\0\1\26\3\27\2\0\1\100\1\0\1\100\6\0"+
    "\1\100\4\0\1\100\1\0\1\100\30\0\1\112\10\0"+
    "\1\113\1\114\1\0\1\115\1\0\1\113\3\0\2\113"+
    "\1\0\2\116\3\113\1\0\1\113\2\0\1\113\1\0"+
    "\1\113\3\0\1\113\1\0\1\113\3\0\1\113\10\0"+
    "\1\117\1\0\1\113\1\120\2\116\1\121\2\0\1\122"+
    "\12\0\1\122\40\0\1\123\2\122\2\0\1\113\1\114"+
    "\1\0\1\115\1\0\1\113\3\0\2\113\1\0\2\116"+
    "\3\113\1\0\1\113\2\0\1\113\1\0\1\113\3\0"+
    "\1\113\1\0\1\113\3\0\1\113\10\0\1\117\1\0"+
    "\1\113\2\116\1\124\1\121\1\0\1\113\1\125\3\0"+
    "\1\113\3\0\2\113\1\0\1\125\1\126\3\113\1\0"+
    "\1\113\2\0\1\113\1\0\1\113\3\0\1\113\1\0"+
    "\1\113\3\0\1\113\10\0\1\117\1\0\1\113\1\126"+
    "\1\125\1\127\1\121\1\0\1\113\1\114\1\0\1\115"+
    "\1\0\1\113\3\0\2\113\1\0\2\116\3\113\1\0"+
    "\1\113\2\0\1\113\1\0\1\113\3\0\1\113\1\0"+
    "\1\113\3\0\1\113\10\0\1\117\1\0\1\113\2\116"+
    "\1\130\1\121\2\0\1\131\12\0\2\131\34\0\1\117"+
    "\2\0\3\131\1\121\2\0\1\122\12\0\1\122\35\0"+
    "\1\117\3\0\2\122\1\121\1\0\1\63\1\0\1\64"+
    "\6\0\1\65\1\66\1\67\4\0\1\65\1\64\1\0"+
    "\2\64\2\0\1\132\32\0\1\63\1\0\1\64\6\0"+
    "\1\65\1\66\1\67\4\0\1\65\1\64\1\0\2\64"+
    "\10\0\1\133\24\0\1\134\1\135\1\134\2\0\7\134"+
    "\2\135\30\134\2\0\2\134\1\136\2\134\3\135\2\0"+
    "\1\113\4\0\1\113\3\0\2\113\3\0\3\113\1\0"+
    "\1\113\2\0\1\113\1\0\1\113\3\0\1\113\1\0"+
    "\1\113\3\0\1\113\12\0\1\113\6\0\1\114\6\0"+
    "\1\62\3\0\2\114\37\0\3\114\2\0\1\137\2\0"+
    "\1\115\1\0\1\137\6\0\2\115\36\0\1\137\3\115"+
    "\2\0\1\137\1\114\1\0\1\115\1\0\1\137\2\0"+
    "\1\62\3\0\2\116\36\0\1\137\3\116\2\0\1\140"+
    "\1\0\1\140\2\0\7\140\2\0\30\140\2\0\2\140"+
    "\1\0\2\140\5\0\1\137\1\114\1\0\1\115\1\0"+
    "\1\137\2\0\1\62\3\0\2\116\36\0\1\137\1\116"+
    "\1\130\1\116\2\0\1\141\1\0\1\141\2\0\7\141"+
    "\2\0\30\141\2\0\2\141\1\0\2\141\6\0\1\122"+
    "\12\0\1\122\41\0\2\122\60\0\1\142\3\0\1\137"+
    "\1\114\1\0\1\115\1\0\1\137\2\0\1\62\3\0"+
    "\2\116\36\0\1\137\1\116\1\143\1\116\3\0\1\125"+
    "\6\0\1\144\3\0\1\125\1\126\37\0\1\126\2\125"+
    "\3\0\1\126\6\0\1\145\3\0\2\126\37\0\3\126"+
    "\3\0\1\125\6\0\1\144\3\0\1\125\1\126\37\0"+
    "\1\126\1\146\1\125\2\0\1\137\1\114\1\0\1\115"+
    "\1\0\1\137\2\0\1\62\3\0\2\116\36\0\1\137"+
    "\1\116\1\147\1\116\3\0\1\131\12\0\2\131\37\0"+
    "\3\131\26\0\1\150\56\0\1\151\40\0\1\134\1\0"+
    "\1\134\2\0\7\134\2\0\30\134\2\0\2\134\1\0"+
    "\1\152\1\134\6\0\1\135\12\0\2\135\35\0\1\153"+
    "\1\154\3\135\2\0\1\155\1\0\1\155\2\0\7\155"+
    "\2\0\30\155\2\0\2\155\1\0\2\155\5\0\1\137"+
    "\4\0\1\137\46\0\1\137\5\0\1\140\1\0\1\140"+
    "\2\0\7\140\2\0\30\140\2\0\2\140\1\153\2\140"+
    "\5\0\1\141\1\0\1\141\2\0\7\141\2\0\30\141"+
    "\2\0\2\141\1\0\2\141\3\0\1\153\57\0\1\156"+
    "\3\0\1\137\1\114\1\0\1\115\1\0\1\137\2\0"+
    "\1\62\3\0\2\116\36\0\1\137\1\116\1\157\1\116"+
    "\2\0\1\160\1\0\1\160\6\0\1\160\4\0\1\160"+
    "\1\0\1\160\41\0\1\161\1\0\1\161\6\0\1\161"+
    "\4\0\1\161\1\0\1\161\42\0\1\125\6\0\1\144"+
    "\3\0\1\125\1\126\37\0\1\126\1\162\1\125\2\0"+
    "\1\137\1\114\1\0\1\115\1\0\1\137\2\0\1\62"+
    "\3\0\2\116\36\0\1\137\1\116\1\163\1\116\32\0"+
    "\1\164\67\0\1\165\76\0\1\153\6\0\1\155\1\0"+
    "\1\155\2\0\7\155\2\0\30\155\2\0\2\155\1\154"+
    "\2\155\63\0\1\166\3\0\1\137\1\114\1\0\1\115"+
    "\1\0\1\137\2\0\1\62\3\0\2\116\36\0\1\137"+
    "\1\116\1\167\1\116\2\0\1\160\1\0\1\160\6\0"+
    "\1\160\4\0\1\160\1\0\1\160\30\0\1\170\10\0"+
    "\1\161\1\0\1\161\6\0\1\161\4\0\1\161\1\0"+
    "\1\161\30\0\1\171\11\0\1\125\6\0\1\144\3\0"+
    "\1\125\1\126\37\0\1\126\1\172\1\125\2\0\1\137"+
    "\1\114\1\0\1\115\1\0\1\137\2\0\1\62\3\0"+
    "\2\116\36\0\1\137\1\116\1\173\1\116\33\0\1\174"+
    "\67\0\1\175\100\0\1\176\3\0\1\137\1\114\1\0"+
    "\1\115\1\0\1\137\2\0\1\62\3\0\2\116\36\0"+
    "\1\137\1\116\1\177\1\116\2\0\1\134\1\200\1\134"+
    "\2\0\7\134\1\200\1\0\30\134\2\0\2\134\1\136"+
    "\2\134\1\0\2\200\3\0\1\201\12\0\1\201\41\0"+
    "\2\201\26\0\1\202\56\0\1\203\116\0\1\204\3\0"+
    "\1\137\1\114\1\0\1\115\1\0\1\137\2\0\1\62"+
    "\3\0\2\116\36\0\1\137\1\116\1\205\1\116\3\0"+
    "\1\200\12\0\1\200\36\0\1\153\1\154\1\0\2\200"+
    "\3\0\1\201\12\0\1\201\36\0\1\153\2\0\2\201"+
    "\34\0\1\206\67\0\1\207\77\0\1\210\31\0\1\211"+
    "\67\0\1\211\24\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[6300];
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
    "\1\0\1\11\17\1\1\11\2\1\3\0\1\1\16\0"+
    "\1\11\13\0\2\1\10\0\1\1\15\0\2\1\1\0"+
    "\1\1\1\0\1\1\1\0\1\1\1\0\2\1\1\0"+
    "\3\1\5\0\1\1\3\0\1\1\2\0\2\1\2\0"+
    "\1\1\1\11\3\0\1\1\2\0\2\1\3\0\1\1"+
    "\2\0\2\1\3\0\1\1\5\0\1\1\2\0\2\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[137];
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
  Fase3(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  Fase3(java.io.InputStream in) {
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
    while (i < 186) {
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
        case 3: 
          { lexeme=yytext(); return  Constante;
          }
        case 9: break;
        case 2: 
          { /*Ignore*/
          }
        case 10: break;
        case 7: 
          { lexeme=yytext(); return  Incorrecta;
          }
        case 11: break;
        case 6: 
          { lexeme=yytext(); return InstruccionCorr;
          }
        case 12: break;
        case 8: 
          { lexeme=yytext(); return  InstruccionCorr;
          }
        case 13: break;
        case 5: 
          { lexeme=yytext(); return Etiqueta;
          }
        case 14: break;
        case 4: 
          { return Linea;
          }
        case 15: break;
        case 1: 
          { return ERROR;
          }
        case 16: break;
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
