

import java.util.ArrayList;
import java.util.Arrays;

public class Principal {
    private static final double PRECISION = 0.0001;

    private enum Test {
        T1( "t1.txt", new ArrayList<>( Arrays.asList(
                new ArrayList<>(),
                new ArrayList<>( Arrays.asList(
                        new Vecteur( 0.1, 0.1 )
                ) ),
                new ArrayList<>( Arrays.asList(
                        new Vecteur( 0.2, 0.19902 )
                ) ),
                new ArrayList<>( Arrays.asList(
                        new Vecteur( 0.1, 0.1 ),
                        new Vecteur( 0.3, 0.29706 )
                ) ),
                new ArrayList<>( Arrays.asList(
                        new Vecteur( 1.03, 0.06 ),
                        new Vecteur( 0.2, 0.19902 ),
                        new Vecteur( 0.4, 0.39412 )
                ) ),
                new ArrayList<>( Arrays.asList(
                        new Vecteur( 0.1, 0.1 ),
                        new Vecteur( 1.06, 0.11902 ),
                        new Vecteur( 0.3, 0.29706 ),
                        new Vecteur( 0.5, 0.4902 )
                ) ),
                new ArrayList<>( Arrays.asList(
                        new Vecteur( 0.2, 0.19902 ),
                        new Vecteur( 1.09, 0.17706 ),
                        new Vecteur( 0.4, 0.39412 )
                ) ),
                new ArrayList<>( Arrays.asList(
                        new Vecteur( 0.1, 0.1 ),
                        new Vecteur( 0.3, 0.29706 ),
                        new Vecteur( 1.12, 0.23412 ),
                        new Vecteur( 0.5, 0.4902 )
                ) ),
                new ArrayList<>( Arrays.asList(
                        new Vecteur( 0.2, 0.19902 ),
                        new Vecteur( 0.4, 0.39411 ),
                        new Vecteur( 1.15, 0.2902 )
                ) ),
                new ArrayList<>( Arrays.asList(
                        new Vecteur( 1.03, 0.06 ),
                        new Vecteur( 0.1, 0.1 ),
                        new Vecteur( 0.3, 0.29706 ),
                        new Vecteur( 0.5, 0.4902 ),
                        new Vecteur( 1.18, 0.3453 )
                ) )
        ) ) ),
        T2( "t2 (1).txt", new ArrayList<>( Arrays.asList(
                new ArrayList<>( Arrays.asList(
                        new Vecteur( 1.0, 0.0 )
                ) )
        ) ) ),
        T3( "t3.txt", new ArrayList<>( Arrays.asList(
                new ArrayList<>( Arrays.asList(
                        new Vecteur( 1.0, 0.0 )
                ) ),
                new ArrayList<>( Arrays.asList(
                        new Vecteur( 1.0, 0.0 )
                ) ),
                        new ArrayList<>( Arrays.asList(
                        new Vecteur( 1.0, 0.0 )
                ) )
        ) ) ),
        T4( "t4.txt", new ArrayList<>( Arrays.asList(
                new ArrayList<>( Arrays.asList(
                        new Vecteur( 1.0, 0.0 )
                ) ),
                new ArrayList<>( Arrays.asList(
                        new Vecteur( 2.0, 0.0 ),
                        new Vecteur( 1.0, 0.0 )
                ) ),
                new ArrayList<>( Arrays.asList(
                        new Vecteur( 3.0, 0.0 ),
                        new Vecteur( 2.0, 0.0 ),
                        new Vecteur( 1.0, 0.0 )
                ) )
        ) ) ),
        T5( "t5.txt", new ArrayList<>( Arrays.asList(
                new ArrayList<>( Arrays.asList(
                        new Vecteur( 1.0, 0.0 )
                ) ),
                new ArrayList<>( Arrays.asList(
                        new Vecteur( 2.0, 1.0 ),
                        new Vecteur( 1.0, 0.0 )
                ) ),
                new ArrayList<>( Arrays.asList(
                        new Vecteur( 3.0, 3.0 ),
                        new Vecteur( 2.0, 1.0 ),
                        new Vecteur( 1.0, 0.0 )
                ) )
        ) ) ),
        ;
        public String nomFichier;
        public ArrayList< ArrayList< Vecteur > > attendu;

        Test( String nomFichier, ArrayList< ArrayList< Vecteur > > attendu ) {
            this.nomFichier = nomFichier;
            this.attendu = attendu;
        }

        private static
        boolean appEgalDouble( double attendu, double obtenu ) {
            return ( Math.abs( attendu - obtenu ) ) <= ( attendu * PRECISION );
        }

        private static
        boolean appEgalVecteur( Vecteur attendu, Vecteur obtenu ) {
            return appEgalDouble( attendu.x(), obtenu.x() ) && appEgalDouble( attendu.y(), obtenu.y() );
        }

        private static
        boolean verifierTic( ArrayList< Vecteur > attendu, ArrayList< Vecteur >  obtenu, int id ) {
            boolean correct = true;

            if( attendu.size() != obtenu.size() ) {
                System.out.println( "  Nombre de projectile incorrect pour le tic : " + id  );
                correct = false;
            } else {
                for( int i = 0; i < attendu.size(); ++ i ) {
                    Vecteur cible = attendu.get( i );
                    int j = 0;
                    while( j < obtenu.size() && ! appEgalVecteur( cible, obtenu.get( j ) ) ) {
                        ++ j;
                    }
                    if( j == obtenu.size() ) {
                        System.out.println( "  tic " + id + " : Le vecteur " + cible + " n'est pas present." );
                        correct = false;
                    } else {
                        obtenu.remove( j );
                    }
                }
            }

            return correct;
        }

        private static
        boolean verifier( ArrayList< ArrayList< Vecteur > > attendu, ArrayList< ArrayList< Vecteur > >  obtenu ) {
            boolean correct = true;

            if( attendu.size() != obtenu.size() ) {
                System.out.println( "Pas le bon nombre de tic pour la simulation." );
                correct = false;
            } else {
                for( int i = 0; i < attendu.size(); ++ i ) {
                    correct = correct && verifierTic( attendu.get( i ), obtenu.get( i ), i );
                }
            }

            return correct;
        }

        public
        void tester() {
            ArrayList< ArrayList< Vecteur > > obtenu = Simulation.simuler( nomFichier );
            System.out.println( "test : " + nomFichier );
            if( verifier( attendu, obtenu ) ) {
                System.out.println( "  -- correct." );
            } else {
                System.out.println( "  -- incorrect." );
            }
        }

        public static
        void toutTester() {
            for( Test test : Test.class.getEnumConstants() ) {
                test.tester();
            }
        }

        @Override
        public String toString() {
            return "" + Simulation.simuler( nomFichier );
        }
    }

    public static void main( String [] argv ) {
        // Pour afficher les résultats d'un test :
         //System.out.println( Test.T5 );

        // Pour lancer tout les tests.
        Test.toutTester();

    }
}
