import org.junit.Test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.BinaryOperator;

public class LambdaExample {
    public void test1() {
        Button button = new Button();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("button pressed");
            }
        });
        button.addActionListener(event->System.out.println("button pressed"));
    }

    @Test
    public void test2(){
        Runnable noArguments = () -> System.out.println("abcd");
        new Thread(noArguments).start();

        ActionListener oneArgument = event -> System.out.println("button pressed");
        Button button = new Button();
        button.addActionListener(oneArgument);

        Runnable mulitStatement = () -> {
            System.out.println("a");
            System.out.println("b");
        };
    }

    @Test
    public void test3(){
        Long q = 12L;
        BinaryOperator<Long> add = (x, y) -> x+y + q;
        Long a = add.apply(3L,4L);
        System.out.println(a);
    }
}
