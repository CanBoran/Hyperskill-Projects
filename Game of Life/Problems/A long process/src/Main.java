startLongProcess(new Callback() {
public void onStarted(){
        System.out.println("The process started");
        }

public void onStopped(String cause) {
        System.out.println(cause);
        }

public void onFinished(int code) {
        if (code == 0) {
        System.out.println("The process successfully finished");
        } else {
        System.out.println("The process is finished with error: " + code);
        }
        }
        });