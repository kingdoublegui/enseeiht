package egg;
import java.util.ResourceBundle;
import mg.egg.eggc.runtime.libjava.messages.NLS;
public class BlocMessages extends NLS {
  private static final long serialVersionUID = 1L;
  private static final String BUNDLE_NAME = "Bloc";
  private BlocMessages() {
  // Do not instantiate
  }
  static {
  NLS.initializeMessages(BUNDLE_NAME, egg.BlocMessages.class);
  }
  private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
  public static ResourceBundle getResourceBundle() {
  return RESOURCE_BUNDLE;
  }		

  public static  String Bloc_already_defined;
  public static  String Bloc_expected_token;
  public static  String BLOC_undefined_ident;
  public static  String Bloc_not_a_type;
  public static  String Bloc_undefined_ident;
  public static  String Bloc_expected_eof;
  public static  String Bloc_unexecpected_token;
  public static  String BLOC_not_a_variable;
  public static  String Bloc_unexpected_token;
  }
