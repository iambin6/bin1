import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.util.Comparator;

public class StudentsManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // a. Nhập số lượng học sinh
        System.out.print("Nhập số lượng học sinh: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        String[] tenHocSinh = new String[n];
        String[] lopHocSinh = new String[n];
        double[] diemHocSinh = new double[n];

        // Nhập tên và lớp cho học sinh
        for (int i = 0; i < n; i++) {
            System.out.print("Nhập tên học sinh thứ " + (i + 1) + ": ");
            tenHocSinh[i] = chuanHoaTen(scanner.nextLine());

            System.out.print("Nhập lớp của học sinh thứ " + (i + 1) + ": ");
            lopHocSinh[i] = scanner.nextLine();
        }

        // b. Khởi tạo ngẫu nhiên điểm từ 1 đến 10
        for (int i = 0; i < n; i++) {
            diemHocSinh[i] = random.nextInt(10) + 1;
        }

        // c. Xuất danh sách học sinh và đánh giá năng lực
        System.out.println("\nDanh sách học sinh và năng lực học tập:");
        for (int i = 0; i < n; i++) {
            String nangLuc = danhGiaNangLuc(diemHocSinh[i]);
            System.out.println("Tên: " + tenHocSinh[i] + ", Lớp: " + lopHocSinh[i] + ", Điểm: " + diemHocSinh[i] + " - Năng lực: " + nangLuc);
        }

        // d. Sắp xếp danh sách học sinh theo lớp giảm dần
        System.out.println("\nDanh sách học sinh sau khi sắp xếp theo lớp:");
        sapXepHocSinhTheoLop(tenHocSinh, lopHocSinh, diemHocSinh);
        for (int i = 0; i < n; i++) {
            System.out.println("Tên: " + tenHocSinh[i] + ", Lớp: " + lopHocSinh[i] + ", Điểm: " + diemHocSinh[i]);
        }

        // e. Tìm học sinh có điểm cao nhất
        int viTriDiemCaoNhat = timHocSinhDiemCaoNhat(diemHocSinh);
        System.out.println("\nHọc sinh có điểm cao nhất:");
        System.out.println("Tên: " + tenHocSinh[viTriDiemCaoNhat] + ", Lớp: " + lopHocSinh[viTriDiemCaoNhat] + ", Điểm: " + diemHocSinh[viTriDiemCaoNhat]);

        // f. Tính điểm trung bình
        double diemTrungBinh = tinhDiemTrungBinh(diemHocSinh);
        System.out.println("\nĐiểm trung bình của các học sinh: " + diemTrungBinh);

        scanner.close();
    }

    // Hàm chuẩn hóa tên học sinh
    public static String chuanHoaTen(String ten) {
        String[] words = ten.toLowerCase().split("\\s+");
        StringBuilder chuanHoaTen = new StringBuilder();
        for (String word : words) {
            chuanHoaTen.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
        }
        return chuanHoaTen.toString().trim();
    }

    // Hàm đánh giá năng lực học sinh dựa vào điểm
    public static String danhGiaNangLuc(double diem) {
        if (diem < 5) {
            return "Yếu";
        } else if (diem < 6.5) {
            return "Trung bình";
        } else if (diem < 7.5) {
            return "Tốt";
        } else if (diem < 9) {
            return "Xuất sắc";
        } else {
            return "Xuất sắc";
        }
    }

    // Hàm sắp xếp học sinh theo lớp giảm dần
    public static void sapXepHocSinhTheoLop(String[] tenHocSinh, String[] lopHocSinh, double[] diemHocSinh) {
        Integer[] indices = new Integer[tenHocSinh.length];
        for (int i = 0; i < indices.length; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return lopHocSinh[i2].compareTo(lopHocSinh[i1]);
            }
        });

        String[] tenTmp = new String[tenHocSinh.length];
        String[] lopTmp = new String[lopHocSinh.length];
        double[] diemTmp = new double[diemHocSinh.length];

        for (int i = 0; i < indices.length; i++) {
            tenTmp[i] = tenHocSinh[indices[i]];
            lopTmp[i] = lopHocSinh[indices[i]];
            diemTmp[i] = diemHocSinh[indices[i]];
        }

        System.arraycopy(tenTmp, 0, tenHocSinh, 0, tenTmp.length);
        System.arraycopy(lopTmp, 0, lopHocSinh, 0, lopTmp.length);
        System.arraycopy(diemTmp, 0, diemHocSinh, 0, diemTmp.length);
    }

    // Hàm tìm học sinh có điểm cao nhất
    public static int timHocSinhDiemCaoNhat(double[] diemHocSinh) {
        int viTri = 0;
        for (int i = 1; i < diemHocSinh.length; i++) {
            if (diemHocSinh[i] > diemHocSinh[viTri]) {
                viTri = i;
            }
        }
        return viTri;
    }

    // Hàm tính điểm trung bình của học sinh
    public static double tinhDiemTrungBinh(double[] diemHocSinh) {
        double tongDiem = 0;
        for (double diem : diemHocSinh) {
            tongDiem += diem;
        }
        return tongDiem / diemHocSinh.length;
    }
}
