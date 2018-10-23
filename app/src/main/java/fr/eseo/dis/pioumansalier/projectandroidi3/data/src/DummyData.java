package fr.eseo.dis.pioumansalier.projectandroidi3.data.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.eseo.dis.pioumansalier.projectandroidi3.data.Jury;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.Project;
    public class DummyData {


        private static List<Project> LIST_PROJECT;
        private static List<Jury> LIST_JURIES;

        private static Project[] PROJECT = new Project[]{
            new Project(1, "OC"),
                new Project(2, "SE"),
                new Project(3, "NRJ")
        };
        private static Jury[] JURY = new Jury[]{
                new Jury(1),
                new Jury(2),
                new Jury(3)
        };

        private DummyData() {
        }

        public static List<Project> getProjects() {
            if (LIST_PROJECT == null) {
                LIST_PROJECT = Arrays.asList(PROJECT);
            }
            return LIST_PROJECT;
        }
        public static List<Jury> getJuries() {
            if (LIST_JURIES== null) {
                LIST_JURIES= Arrays.asList(JURY);
            }
            return LIST_JURIES;
        }


}
