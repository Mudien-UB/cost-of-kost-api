package com.hehe.cost_control_api.util;

import com.hehe.cost_control_api.model.FeedbackMessage;
import com.hehe.cost_control_api.model.enums.TypeLevel;
import com.hehe.cost_control_api.repository.FeedbackMessageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class FeedbackMessageSeeder implements CommandLineRunner {

    private final FeedbackMessageRepository repository;

    public FeedbackMessageSeeder(FeedbackMessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() == 0) {
            LocalDateTime now = LocalDateTime.now();

            List<FeedbackMessage> feedbacks = List.of(
                    new FeedbackMessage(null, 0F, 0F,
                            "Versi terbaik dari dirimu sedang kamu bangun hari ini.",
                            "Kamu mungkin belum sampai tujuan, tapi kamu sudah jauh dari titik awal. Kamu sedang belajar mengatur hidup dan keuangan—dan itu adalah sebuah pencapaian. Setiap langkah kecil yang kamu ambil hari ini akan membawa perubahan besar di masa depan.",
                            "Teruslah konsisten, karena kedisiplinan hari ini adalah kebebasan esok hari.",
                            now, now, TypeLevel.DEFAULT),

                    new FeedbackMessage(null, 0.0f, 20.0f,
                            "Awal perubahan memang tidak mudah",
                            "Memulai pengelolaan keuangan bukan soal langsung sempurna, tapi soal keberanian untuk menghadapi dan memahami pola hidup yang selama ini berjalan tanpa kontrol.",
                            "Perubahan berarti kamu berani bertanggung jawab atas dirimu sendiri.",
                            now, now, TypeLevel.LEVEL_1),

                    new FeedbackMessage(null, 0.0f, 20.0f,
                            "Kesadaran adalah langkah pertama",
                            "Meski pencatatan belum terlihat berdampak, kamu sedang membangun pondasi keuangan yang kuat. Tidak semua orang sampai di tahap ini, dan itu patut diapresiasi.",
                            "Setiap pencatatan kecil membentuk disiplin besar di masa depan.",
                            now, now, TypeLevel.LEVEL_1),

                    new FeedbackMessage(null, 0.0f, 20.0f,
                            "Proses itu penting, bukan hasil instan",
                            "Ketika kamu mulai peduli terhadap pengeluaran, kamu telah memasuki tahap paling penting: memahami. Waktu dan konsistensi akan mengubah arah keuanganmu secara bertahap.",
                            "Tidak apa-apa belum sempurna, yang penting kamu terus mencoba.",
                            now, now, TypeLevel.LEVEL_1),



                    new FeedbackMessage(null, 20.0f, 40.0f,
                            "Perubahan sedang terjadi perlahan",
                            "Pengeluaranmu mulai tercatat, dan itu artinya kamu belajar membaca diri sendiri. Pengelolaan keuangan yang baik selalu dimulai dari keberanian untuk jujur pada kebiasaan.",
                            "Kamu sedang membangun sistem kontrol dalam hidupmu sendiri.",
                            now, now, TypeLevel.LEVEL_2),

                    new FeedbackMessage(null, 20.0f, 40.0f,
                            "Belajar mengontrol bukan hal instan",
                            "Kamu mulai bisa menahan dorongan belanja impulsif dan mencoba berpikir ulang sebelum membelanjakan. Proses ini penting untuk membentuk pola hidup finansial yang sehat.",
                            "Setiap jeda sebelum membeli adalah latihan kesadaran diri.",
                            now, now, TypeLevel.LEVEL_2),

                    new FeedbackMessage(null, 20.0f, 40.0f,
                            "Kamu sedang membentuk kebiasaan baru",
                            "Konsistensi dalam mencatat meskipun belum ideal adalah pondasi dari perubahan jangka panjang. Setiap hari kamu sedang mengajarkan dirimu untuk lebih bertanggung jawab.",
                            "Lanjutkan walau pelan. Disiplin dibangun lewat pengulangan.",
                            now, now, TypeLevel.LEVEL_2),



                    new FeedbackMessage(null, 40.0f, 60.0f,
                            "Keseimbangan mulai terbentuk",
                            "Pengeluaranmu sudah lebih terkendali. Kamu mulai bisa membaca pola hidupmu dan menyesuaikannya dengan kemampuan. Ini pencapaian yang patut dipertahankan dan ditingkatkan.",
                            "Jadikan keseimbangan ini sebagai standar hidupmu ke depan.",
                            now, now, TypeLevel.LEVEL_3),

                    new FeedbackMessage(null, 40.0f, 60.0f,
                            "Perjalananmu sudah separuh jalan",
                            "Pencatatanmu sudah membentuk pola dan menunjukkan arah yang lebih jelas. Kamu mulai mengerti bagaimana mengatur kebutuhan dan menjaga emosi saat berbelanja.",
                            "Teruskan tanpa lengah. Konsistensi adalah kunci menuju stabilitas.",
                            now, now, TypeLevel.LEVEL_3),

                    new FeedbackMessage(null, 40.0f, 60.0f,
                            "Disiplinmu mulai terasa manfaatnya",
                            "Pengambilan keputusanmu semakin bijak dan pengeluaran lebih terstruktur. Kamu sedang membangun ritme hidup yang tidak hanya hemat tapi juga strategis dan terukur.",
                            "Gunakan momentum ini untuk melangkah lebih jauh.",
                            now, now, TypeLevel.LEVEL_3),




                    new FeedbackMessage(null, 60.0f, 80.0f,
                            "Ritme hidupmu mulai terstruktur",
                            "Pola pengeluaranmu mulai menunjukkan konsistensi yang sehat. Ini pertanda bahwa kamu sudah mulai mengintegrasikan prinsip keuangan dalam setiap keputusan sehari-hari.",
                            "Pertahankan pola ini dengan terus mengevaluasi diri secara berkala.",
                            now, now, TypeLevel.LEVEL_4),

                    new FeedbackMessage(null, 60.0f, 80.0f,
                            "Kesadaranmu tumbuh bersama kebiasaanmu",
                            "Kamu bukan hanya mencatat, tapi juga mulai menganalisis dan berpikir dua kali sebelum mengeluarkan uang. Itulah tanda bahwa kamu telah menjadikan kontrol sebagai gaya hidup.",
                            "Terus latih kemampuan refleksi keuangan harianmu.",
                            now, now, TypeLevel.LEVEL_4),

                    new FeedbackMessage(null, 60.0f, 80.0f,
                            "Kontrol bukan lagi paksaan, tapi pilihan sadar",
                            "Ketika kebiasaan mengatur keuangan berjalan tanpa tekanan, artinya kamu telah melewati fase adaptasi. Kamu mulai merasa nyaman dalam disiplin dan pengendalian diri.",
                            "Tetap tenang, tapi jangan berhenti bertumbuh.",
                            now, now, TypeLevel.LEVEL_4),




                    new FeedbackMessage(null, 80.0f, 100.0f,
                            "Kamu sudah berada di jalur yang berkelanjutan",
                            "Kebiasaan finansialmu telah menunjukkan kedisiplinan yang jarang ditemukan. Kamu tidak hanya mengatur uang, tapi juga mengelola pikiran dan perilaku konsumtif dengan sadar.",
                            "Pertahankan dan jadikan ini bekal menghadapi tantangan finansial berikutnya.",
                            now, now, TypeLevel.LEVEL_5),

                    new FeedbackMessage(null, 80.0f, 100.0f,
                            "Disiplinmu mencerminkan kematangan berpikir",
                            "Setiap langkah yang kamu ambil berdasarkan perencanaan menunjukkan bahwa kamu memahami nilai uang, waktu, dan keputusan. Kamu telah menciptakan ritme yang terukur.",
                            "Jangan puas. Terus cari cara memperkuat fondasi ini.",
                            now, now, TypeLevel.LEVEL_5),

                    new FeedbackMessage(null, 80.0f, 100.0f,
                            "Refleksi dan kontrol sudah menjadi kebiasaanmu",
                            "Kamu telah mencapai tahap di mana pengelolaan keuangan bukan lagi beban, tapi bagian dari hidup. Ini hasil dari kesabaran, refleksi, dan kemauan untuk terus berkembang.",
                            "Gunakan pencapaian ini sebagai pijakan untuk mimpi yang lebih besar.",
                            now, now, TypeLevel.LEVEL_5)

                    );

            repository.saveAll(feedbacks);
            System.out.println("✅ FeedbackMessage seeding selesai.");
        }
    }
}
