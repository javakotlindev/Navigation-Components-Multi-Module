# Navigation-Components-Multi-Module
Here will be methods of using Navigation Components in multi-module project

**Method 1:**
![image](https://user-images.githubusercontent.com/60436279/201531132-0ce4b7c4-6d04-4b7b-9035-0fb8077042d8.png)

For example, we have 4 modules: 2 modules with Feature-Fragments, App module and Navigation module.
In **App _module_** we have Application, MainActivity and etc.. Also in this module we implement all **Feature modules**, so we can create navigation xml class in our resources and create navGraph.

To Avoid Circular Dependecy, we use Navigations module, where we have:
    
   a. Enum-class where we have our navigation screen names (**NavGraphs.kt** in my case)
    
```ruby 
enum class NavGraph {
    LOGIN,
    REGISTER,
    MAIN
}
```
   
   b. Navigation types interface (**Navigatios.kt** in my case):
   
```ruby    
import android.os.Bundle
interface Navigations {
    fun navigate(graph: NavGraph, args: Bundle? = null)
    fun popBackStack()
}
```
  
We implement library in App module to overide interface in our MainActivity and by **NavGraphs.kt** we can navigate to screen that we want. 

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

Then we implement **Navigations** module in **Feature module**s, so we can create instance of Navigations interface and use it instead of Navcontroller and by **NavGraphs.kt**
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
