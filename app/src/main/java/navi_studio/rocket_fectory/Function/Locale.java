package navi_studio.rocket_fectory.Function;

public class Locale {
    String script_num = "";
    String message = "";
    public Locale(String _script_num,String _message)
    {
        script_num=_script_num;
        message=_message;
    }

    public String getMessage()
    {
        return message;
    }
    public void setMessage(String _message)
    {
        message=_message;
    }
    public String getScrpitNumber()
    {
        return script_num;
    }
    public void setScriptNumber(String _script_number)
    {
        script_num=_script_number;
    }
}