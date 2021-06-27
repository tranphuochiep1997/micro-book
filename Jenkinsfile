node {
    stage ('Checkout'){
        git branch: 'main', url: 'https://github.com/tranphuochiep1997/micro-book.git'
    }

    stage ('Build'){
        sh 'mvn -DskipTests clean package'
    }
	
    stage ('Deploy'){
    	sh "ansible-playbook -i /etc/ansible/hosts deploy-playbook.yml -e 'JAR_FILE=${env.WORKSPACE}/target/book-0.0.1-SNAPSHOT.jar'"
    }
}    