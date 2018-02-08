def call(body) {

        def config = [:]
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body.delegate = config
        body()

        pipeline {
    		agent {
        		label 'master'
    		}
    		environment {
     		 PLAYBOOK_PATH = "~/workspace/JenkinsFile_GIT_Repo/ansible/playbooks/flex"
   			}

   			parameters {
        		choice(
            		// choices are a string of newline separated values
            		choices: '\rabbitmq\nredis\ntusker\npheme\ndroms\nopenadr2b\nceep\nndianoga\ncascade\nrtcc\nfam-backend\nall',
            		description: '',
            		name: 'REQUESTED_ACTION')
    		}

    		stages {
    			stage ('demo-preview-rabbitmq') {
    				when {
                		// Only say hello if a "rabbitmq" is requested
                		expression { params.REQUESTED_ACTION == 'rabbitmq'  || params.REQUESTED_ACTION == 'all'}
            		}
            		steps {
                		echo 'Depoying demo-preview-rabbitmq'
                
                		sh "cd ~/workspace/JenkinsFile_GIT_Repo/ansible/playbooks/flex && cp ~/ansible.cfg ansible.cfg && sudo ansible-playbook -i ~/workspace/JenkinsFile_GIT_Repo/ansible/playbooks/flex/inventory/${config.projectName} ~/workspace/JenkinsFile_GIT_Repo/ansible/playbooks/flex/rabbitmq.yml --tags update --vault-password-file  ~/.agv"

            		}
                }
           	}
        }
    }
