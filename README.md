# Navigation-Components-Multi-Module
Here will be methods of using Navigation Components in multi-module project

**Method 1:**
![image](https://user-images.githubusercontent.com/60436279/201531132-0ce4b7c4-6d04-4b7b-9035-0fb8077042d8.png)

For example, we have 4 modules: 2 **Feature** modules (Fragments), **App** module and **Navigation** module. In the **App** module, we have Application, MainActivity, etc.. Also in this module we implement all the Feature modules, so we create a navigation.xml class in our resources and create navGraph.

To avoid circular dependency, we use the **Navigation** module where we have:

a. Enum-class, where we have the names of our navigraphs (navGraphs.kt in my case)
```ruby 
enum class NavGraph {
    LOGIN,
    REGISTER,
    MAIN
}
```
   
b. Interface of navigation types (**Navigatios.kt** in my case):
   
```ruby    
import android.os.Bundle
interface Navigations {
    fun navigate(graph: NavGraph, args: Bundle? = null)
    fun popBackStack()
}
```
  
We implement **Navigation** module in App module to overide interface methods in our MainActivity and with **NavGraphs.kt** we can navigate to the screen we want.

```ruby

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.anynews.myapplication.navigations.NavGraph
import com.anynews.myapplication.navigations.Navigations

class MainActivity : AppCompatActivity(), Navigations {

    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

    }

    override fun navigate(graph: NavGraph, args: Bundle?) {
        when (graph) {
            NavGraph.LOGIN -> navController.navigate(R.id.action_registerFragment_to_loginFragment)
            NavGraph.REGISTER -> navController.navigate(R.id.action_loginFragment_to_registerFragment, args)
            NavGraph.MAIN -> navController.navigate(R.id.action_registerFragment_to_mainFragment)
        }
    }

    override fun popBackStack() {
        navController.popBackStack()
    }
}
```

Then we implement the **Navigation** module in **Feature** modules so we can create an instance of the_**Navigations.kt**_ interface and use it instead of Navcontroller. Then we use _**NavGraphs.kt**_ to pass the name of the screen we want to navigate to
```ruby
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.anynews.myapplication.login_page.databinding.FragmentLoginBinding
import com.anynews.myapplication.navigations.NavGraph
import com.anynews.myapplication.navigations.Navigations

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private lateinit var navController: Navigations

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navController = requireActivity() as Navigations
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nextBtn.setOnClickListener {
            navController.navigate(NavGraph.REGISTER, bundleOf(MESSAGE to REGISTER_PAGE))
        }
    }

    companion object {
        const val MESSAGE = "MESSAGE"
        const val REGISTER_PAGE = "Экран Регистрации"
    }
}
```
