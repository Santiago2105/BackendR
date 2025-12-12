package pe.edu.upc.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pe.edu.upc.backend.dtos.DTOUser;
import pe.edu.upc.backend.entities.*;
import pe.edu.upc.backend.repositories.CourseRepository;
import pe.edu.upc.backend.repositories.FacultyRepository;
import pe.edu.upc.backend.repositories.MajorRepository;
import pe.edu.upc.backend.repositories.StudentRepository;
import pe.edu.upc.backend.services.AuthorityService;
import pe.edu.upc.backend.services.UserService;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner startConfiguration(
        FacultyRepository facultyRepository,
        MajorRepository majorRepository,
        StudentRepository studentRepository,

        UserService userService,
        AuthorityService authorityService,
        CourseRepository courseRepository


    ){
        return args->{

            Authority authority1 = authorityService.add(new Authority(null,"ROLE_ADMIN",null));
            Authority authority2 = authorityService.add(new Authority(null,"ROLE_PRODUCTOR",null));
            Authority authority3 = authorityService.add(new Authority(null,"ROLE_ASSIST",null));



            userService.add(new DTOUser(null,"gmorip","pass","ROLE_ADMIN;ROLE_PRODUCTOR"));
            userService.add(new DTOUser(null,"qwerty","pass","ROLE_PRODUCTOR"));
            userService.add(new DTOUser(null,"asdfg","pass","ROLE_ASSIST"));







            Faculty faculty = new Faculty(null,"Ingeniera","Jimmy",true,
                                            LocalDate.of(1996,3,10),null,null);
            facultyRepository.save(faculty);
            System.out.println(faculty);
            faculty.setFoundationDate( LocalDate.of(1999,3,10));
            facultyRepository.save(faculty);
            System.out.println(faculty);



            facultyRepository.save(new Faculty(null,"Arquitectura","Alicia",true,
                                            LocalDate.of(2012,7,20),null,null));
            facultyRepository.save(new Faculty(null,"Diseño","Cinthy",true,
                    LocalDate.of(2018,3,3),null,null));
            Faculty f2 = facultyRepository.save(new Faculty(null,"Derecho","Juan",true,
                    LocalDate.of(2001,7,13),null,null));
            System.out.println(f2);

            facultyRepository.save(new Faculty(null,"Musica","Francisco",true,
                    LocalDate.of(2019,3,11),null,null));
            facultyRepository.save(new Faculty(null,"Medicina","Cinthy",true,
                    LocalDate.of(2017,3,14),null,null));
            System.out.println("----------------");

            Faculty f3 = facultyRepository.findById(6L).get();
            System.out.println(f3);

            Faculty f4 = facultyRepository.findById(99L).orElse(null);
            System.out.println(f4);

            System.out.println("----------------");

            List<Faculty> facultyList = facultyRepository.findAll();
            System.out.println("Facultades: "+facultyList.size());
            System.out.println("----------------");
            facultyList.forEach(System.out::println);

            /*
            // Si funciona lo comentamos para tener la lista completa
            facultyRepository.delete(faculty);
            facultyRepository.deleteById(5L);
            */

            facultyList = facultyRepository.findAll();
            System.out.println("Facultades: "+facultyList.size());
            System.out.println("----------------");
            facultyList.forEach(System.out::println);
            System.out.println("----------------");
            Faculty f5 = facultyRepository.findByName("Arquitectura");
            System.out.println(f5);

            System.out.println("----------------");
            List<Faculty> facultyListCinthy = facultyRepository.findByDirector("Cinthy");
            System.out.println("Facultades: "+facultyListCinthy.size());
            System.out.println("----------------");
            facultyListCinthy.forEach(System.out::println);

            majorRepository.save(new Major(null,"Ingenieria de Software",faculty,null));
            majorRepository.save(new Major(null,"Ingenieria de Sistemas de Informacion",faculty,null));
            majorRepository.save(new Major(null,"Ingenieria Civil",faculty,null));
            Major majorErrado = majorRepository.save(new Major(null,"Ingeniera Electronica",faculty,null));
            majorErrado.setName("Ingenieria Economica");
            majorRepository.save(majorErrado);
            majorRepository.save(new Major(null,"Diseño Grafico",
                    facultyRepository.findByName("Diseño"),null));
            majorRepository.save(new Major(null,"Diseño de Interiores",
                    facultyRepository.findByName("Diseño"),null));
            majorRepository.save(new Major(null,"Diseño Industrial",
                    facultyRepository.findByName("Diseño"),null));

            System.out.println("----------------");
            System.out.println("Total Majors");
            List<Major> totalCarreras = majorRepository.findAll();
            totalCarreras.forEach(System.out::println);
            System.out.println("----------------");

            System.out.println("----------------");
            System.out.println("Ingenieria Majors");
            List<Major> carrerasIng = majorRepository.findByFaculty_Id(1L);
            carrerasIng.forEach(System.out::println);
            System.out.println("----------------");

            studentRepository.save(new Student(null,"Gonzalo", "Lima", 45,majorRepository.findById(1L).get(),null));
            studentRepository.save(new Student(null,"Gladys", "Cuzco", 21,majorRepository.findById(1L).get(),null));
            studentRepository.save(new Student(null,"Cinthy", "Lima", 11,majorRepository.findById(1L).get(),null));
            studentRepository.save(new Student(null,"Emma", "Lima", 36,majorRepository.findById(3L).get(),null));
            studentRepository.save(new Student(null,"Ana Paula", "Lima", 49,majorRepository.findById(3L).get(),null));
            studentRepository.save(new Student(null,"Walter", "Amazonas", 14,majorRepository.findById(3L).get(),null));
            studentRepository.save(new Student(null,"Pablo", "Amazonas", 22,majorRepository.findById(3L).get(),null));

            System.out.println("----------------");
            System.out.println("Estudiantes de Lima");
            //Alternativa 1: Dame todos los de la tabla y se filtra en el programa
            //List<Student> studentsFiltrado = studentRepository.findAll().stream().filter(s->s.getCity().equals("Lima")).toList();

            List<Student> studentsFiltrado = studentRepository.findAll().stream().filter(s->s.getCity().equals("Lima")).toList();
            studentsFiltrado.forEach(System.out::println);
            System.out.println("----------------");
            studentsFiltrado = studentRepository.findByCity("Lima");
            studentsFiltrado.forEach(System.out::println);
            System.out.println("----------------");
            studentsFiltrado = studentRepository.findByCitySQL("Lima");
            studentsFiltrado.forEach(System.out::println);
            System.out.println("----------------");
            studentsFiltrado = studentRepository.findByCityJPQL("Lima");
            studentsFiltrado.forEach(System.out::println);

            System.out.println("----------------");
            System.out.println("Estudiantes Creditos > 30");
            studentsFiltrado = studentRepository.findByCreditsGreaterThan(30);
            studentsFiltrado.forEach(System.out::println);

            System.out.println("----------------");
            System.out.println("Estudiantes Creditos ENTRE 20 y 40");
            studentsFiltrado = studentRepository.findByCreditsBetween(20,40);
            studentsFiltrado.forEach(System.out::println);

            System.out.println("----------------");
            System.out.println("Estudiantes con Letra G");
            studentsFiltrado = studentRepository.findByNameContaining("G");
            studentsFiltrado.forEach(System.out::println);

            System.out.println("----------------");
            System.out.println("Estudiantes Creditos ENTRE 20 y 40, ORDENADOR por Nombre Ascendente");
            studentsFiltrado = studentRepository.findByCreditsBetweenOrderByNameAsc(20,40);
            studentsFiltrado.forEach(System.out::println);

            //--------------
            courseRepository.save(new Course(null,"Programacion 1",5,46,null));
            courseRepository.save(new Course(null,"Programacion 2",4,22,null));
            courseRepository.save(new Course(null,"Matematica 1",3,36,null));
            courseRepository.save(new Course(null,"Matematica 2",5,30,null));
            courseRepository.save(new Course(null,"Lenguaje 1",4,24,null));
            courseRepository.save(new Course(null,"Lenguaje 2",5,42,null));

        };
    }

}
