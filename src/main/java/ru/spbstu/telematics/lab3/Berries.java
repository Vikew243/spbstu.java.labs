package ru.spbstu.telematics.lab3;

/*Ягоды в саду. Два враждующих соседа разделены полем с ягодами.
Они разрешили друг другу выходить на поле и собирать ягоды, но им нужно быть уверенным
что только один из них находится на поле в каждый момент времени. После долгих споров они приняли
следующий протокол действий: Когда один из соседей хочет пойти на поле, он поднимает флаг.
Если он видит флаг своего соседа, он не заходит на поле и спускает свой флаг и пробует снова.
Если он не видит флага соседа, он входит на поле и собирает ягоды. Он опускает свой флаг сразу как выйдет с поля.
Смоделировать эту ситуацию. Имеется два соседа, N1 и N2. Необходимо обеспечить раздельный доступ к полю
в соответствии с протоколом. Добавить свойство “Progress” для соседей (количество ягод), чтобы контролировать
справедливость протокола.
 */

public class Berries {

    static class Field{
        private static int berries = 200;
        static void setNewQuantityOfBerries(int takenBerries){
            berries -= takenBerries;
        }
        static int getQuantityOfBerries(){
            return berries;
        }
    }

    static Field field = new Field();

    static class Neibor implements Runnable{
        int progress;
        Neibor(){
            progress = 0;
        }

        int getBerries(){
            return progress;
        }

        void takeBerries(int quantityOfBerries){
            progress += quantityOfBerries;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted() && field.getQuantityOfBerries() > 0) {
            synchronized (field) {
                if (field.getQuantityOfBerries() > 0) {
                        this.takeBerries(5);
                        field.setNewQuantityOfBerries(5);
                        System.out.println(Thread.currentThread().getName() + " taken 5 berries");
                }
            }
        }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Neibor N1 = new Neibor();
        Neibor N2 = new Neibor();
        Thread T1 = new Thread(N1);
        Thread T2 = new Thread(N2);

        checkSum(N1, N2, field);
        T1.start();
        T2.start();
        T1.join();
        T2.join();
        checkSum(N1, N2, field);
    }

    private static void checkSum(Neibor N1, Neibor N2, Field field){
        int result = field.getQuantityOfBerries() + N1.getBerries() + N2.getBerries();
        System.out.println("Result sum = " + result);
        System.out.println("field = " + field.getQuantityOfBerries() + " N1 = " + N1.getBerries() + " N2 = " + N2.getBerries());
    }
}
