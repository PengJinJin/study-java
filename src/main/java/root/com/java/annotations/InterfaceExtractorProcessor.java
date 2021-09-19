package root.com.java.annotations;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.declaration.MethodDeclaration;
import com.sun.mirror.declaration.Modifier;
import com.sun.mirror.declaration.ParameterDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

// APT-based annotation processing.
// {Exec: apt -factory annotations.InterfaceExtractorProcessorFactory Multiplier.java -s ../annotations}
public class InterfaceExtractorProcessor implements AnnotationProcessor {

	private final AnnotationProcessorEnvironment env;
	private ArrayList<MethodDeclaration> interfaceMethods = new ArrayList<>();

	public InterfaceExtractorProcessor(AnnotationProcessorEnvironment env) {
		this.env = env;
	}

	@Override
	public void process() {
		for (TypeDeclaration typeDecl : env.getSpecifiedTypeDeclarations()) {
			ExtractInterface annot = typeDecl.getAnnotation(ExtractInterface.class);
			if (annot == null) break;
			for (MethodDeclaration m : typeDecl.getMethods()) {
				if (m.getModifiers().contains(Modifier.PUBLIC)
						&& !m.getModifiers().contains(Modifier.STATIC)) {
					interfaceMethods.add(m);
				}
			}
			if (interfaceMethods.size() > 0) {
				try {
					PrintWriter writer = env.getFiler().createSourceFile(annot.value());
					writer.println("package " + typeDecl.getPackage().getQualifiedName() + ";");
					writer.println("public interface " + annot.value() + " {");
					for (MethodDeclaration m : interfaceMethods) {
						writer.println("	public ");
						writer.println(m.getReturnType() + " ");
						writer.println(m.getSimpleName() + " (");
						int i = 0;
						for (ParameterDeclaration parm : m.getParameters()) {
							writer.println(parm.getType() + " " + parm.getSimpleName());
							if (++i < m.getParameters().size()) {
								writer.println(",");
							}
						}
						writer.println(");");
					}
					writer.println("}");
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
