package java8.ex04;

import java8.data.Account;
import java8.data.Data;
import java8.data.Person;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/**
 * Exercice 04 - FuncCollection Exercice synthèse des exercices précédents
 */
public class Lambda_04_Test {

	// tag::interfaces[]
	static interface GenericPredicate<T> {
		// TODO
		boolean test(T t);
	}

	interface GenericMapper<T, E> {
		// TODO
		E map(T t);
	}

	interface Processor<T> {
		// TODO
		void process(T t);
	}
	// end::interfaces[]

	// tag::FuncCollection[]
	class FuncCollection<T> {

		private Collection<T> list = new ArrayList<>();

		public void add(T a) {
			list.add(a);
		}

		public void addAll(Collection<T> all) {
			for (T el : all) {
				list.add(el);
			}
		}
		// end::FuncCollection[]

		// tag::methods[]
		private FuncCollection<T> filter(GenericPredicate<T> predicate) {
			FuncCollection<T> result = new FuncCollection<>();
			for (T item : list) {
				if (predicate.test(item)) {
					result.add(item);
				}
			}
			return result;
		}

		private <E> FuncCollection<E> map(GenericMapper<T, E> mapper) {
			
			FuncCollection<E> result = new FuncCollection<>();

			for (T m : list) {

				if (m != null) {

					result.add(mapper.map(m));

				}
			}

			return result;
		}

		private void forEach(Processor<T> processor) {
			// TODO
			for (T p : list) {

				processor.process(p);

			}

		}
		// end::methods[]

	}

	// tag::test_filter_map_forEach[]
	@Test
    public void test_filter_map_forEach() throws Exception {

        List<Person> personList = Data.buildPersonList(100);
        
        
        FuncCollection<Person> personFuncCollection = new FuncCollection<>();
        personFuncCollection.addAll(personList);
        personFuncCollection.filter(p-> p.getAge()>50)
        
        // TODO transformer la liste de personnes en liste de comptes. Un compte a par défaut un solde à 1000.
        .map(p-> {   Account ac = new Account() ;
                			ac.setOwner(p);
                			ac.setBalance(1000);
                			return ac;})
                // TODO vérifier que chaque compte a un solde à 1000.
                // TODO vérifier que chaque titulaire de compte a un age > 50
              
                     
                .forEach(c -> { assert   c.getOwner().getAge()>50 && c.getBalance() ==1000; } );

        // TODO à supprimer
      //  assert false;
    }
	// end::test_filter_map_forEach[]

	// tag::test_filter_map_forEach_with_vars[]
	@Test
	public void test_filter_map_forEach_with_vars() throws Exception {

		List<Person> personList = Data.buildPersonList(100);
		FuncCollection<Person> personFuncCollection = new FuncCollection<>();
		personFuncCollection.addAll(personList);

		// TODO créer une variable filterByAge de type GenericPredicate
		// TODO filtrer, ne garder uniquement que les personnes ayant un age > 50
		// ??? filterByAge = ???;
		
		
		
		
		
	 
		GenericPredicate<Person> filterByAge = t -> t.getAge()>50;
	   
	
		/*GenericPredicate<Person> filterAge = new GenericPredicate<Person>() {

			@Override
			public boolean test(Person t) {
				
				
				// TODO Auto-generated method stub
				return t.getAge()>50;
			}
			
		};
	
	*/
		
		// TODO créer un variable mapToAccount de type GenericMapper
		// TODO transformer la liste de personnes en liste de comptes. Un compte a par
		// défaut un solde à 1000.
		// ??? mapToAccount = ???;

	
	    GenericMapper<Person,Account> mapToAccount = p->{ Account ac = new Account() ;
		ac.setOwner(p);
		ac.setBalance(1000);
		return ac;}; 
	
		// TODO créer un variable verifyAccount de type GenericMapper
		// TODO vérifier que chaque compte a un solde à 1000.
		// TODO vérifier que chaque titulaire de compte a un age > 50
		// ??? verifyAccount = ???;

		Processor<Account> verifyAccount = ac -> {

			assert ac.getBalance().equals(1000);

			assert ac.getOwner().getAge() > 50;

		};
		
		
		
		 personFuncCollection .filter(filterByAge) .map(mapToAccount).forEach(verifyAccount);
		 

		 

		
		
		// TODO A supprimer
		//assert false;
	}
	// end::test_filter_map_forEach_with_vars[]

}
