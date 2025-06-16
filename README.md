# Cost of Kost API

EduSims adalah aplikasi web interaktif yang dirancang untuk membantu mahasiswa dan pencari kost dalam merencanakan pengeluaran bulanan secara edukatif, sederhana, dan menyenangkan.

project frontend nya ada di https://github.com/Mudien-UB/Cost-of-Kost

## Teknologi yang Digunakan
 - Java 21
 - Spring Boot 
 - Spring Data JPA 
 - Lombok 
 - Maven
 - PostgreSql

## Menjalanakan di local

1. clone repository
```bash
    git clone https://github.com/Mudien-UB/cost-of-kost-api.git
```
2. masuk ke direktori
```bash
  cd cost-of-kost-api
```
3. Salin dan Ubah Konfigurasi application.properties

```bash 
    cp ./src/main/resources/application-example.properties ./src/main/resources/application.properties
```

Lalu edit file application.properties:
```properties
# ubah bagian ini

# Ubah bagian ini sesuai konfigurasi PostgreSQL-mu
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

# JWT Configuration
jwt.secret_key=YOUR-VERY-MOST-SECURE-RANDOM-SECRET-KEY
jwt.issuer=YOUR_NAME_OR_ORGANIZATION

```
> Pastikan PostgreSQL berjalan dan database yang dibutuhkan sudah tersedia.

4. clean install depedency

```bash
    ./mvnw clean install
```

> Jika ./mvnw tidak jalan, pastikan kamu memiliki Maven atau gunakan IDE seperti IntelliJ / Eclipse yang mendukung Maven.

5. jalankan project

```bash
    ./mvnw spring-boot:run
```

secara default aplikasi berjalan di:

```markdown
http://localhost:8080
```

> Jika port 8080 bentrok, kamu bisa mengubahnya di application.properties
```properties
server.port=8081

```

## Pengujian API

Gunakan Postman, cURL, atau Insomnia untuk menguji endpoint API.

##  Catatan
- Proyek ini menggunakan JWT untuk otentikasi — pastikan secret key aman. 
- Struktur endpoint dapat dilihat di folder controller/. 
- Entity dan model terdapat di folder model/.

### ⚠️ Catatan Penting untuk Lombok

Agar anotasi Lombok seperti `@Getter`, `@Setter`, dan lainnya bisa dikenali dengan baik oleh IDE:

1. Install plugin Lombok di IDE (IntelliJ atau Eclipse)
2. Aktifkan **annotation processing** di pengaturan IDE:
    - IntelliJ:  
      `Settings > Build, Execution, Deployment > Compiler > Annotation Processors > Enable annotation processing`
3. Jika error tetap muncul, jalankan:
   ```bash
   ./mvnw clean install

---

### ❗️Troubleshooting Lombok

Jika kamu mengalami error terkait Lombok seperti:

- Anotasi `@Getter`, `@Setter`, dll tidak dikenali oleh IDE
- Error `Cannot resolve method getX()`
- Build gagal karena class tidak memiliki method tertentu

Silakan pastikan kamu sudah:

1. Mengaktifkan **annotation processing** di IDE kamu
2. Menginstal plugin **Lombok** di IntelliJ/Eclipse
3. Menjalankan `./mvnw clean install` dari terminal

Jika semua sudah dilakukan dan error masih terjadi, **jangan ragu untuk membuka issue** di repository ini:

 [Buka Issue](https://github.com/Mudien-UB/cost-of-kost-api/issues)

Sertakan:
- Screenshot error
- Versi IDE (IntelliJ/Eclipse)
- Versi Java yang digunakan
- Sistem operasi

Kami akan membantu sesegera mungkin 😊

## THANKS
> dont forget a cup of coffee today

---
