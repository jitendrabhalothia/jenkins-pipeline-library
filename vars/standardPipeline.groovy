def call(body) {

        def config = [:]
        def PLAYBOOK_PATH = "~/workspace/JenkinsFile_GIT_Repo/ansible/playbooks"
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body.delegate = config
        
        body()

        pipeline {
            agent {
                label 'master'
            }
            
             parameters {
                choice(
                    // choices are a string of newline separated values
                    choices: 'rabbitmq\nredis\ntusker\npheme\ndroms\nopenadr2b\nceep\nndianoga\ncascade\nrtcc\nfam-backend\nall',
                    description: '',
                    name: 'REQUESTED_ACTION')
            }

            stages {
                stage ('rabbitmq') {
                    
                    steps {
                        script {
                            if (params.REQUESTED_ACTION == 'rabbitmq'){
                                echo 'Depoying rabbitmq'
                                sh "cd ${PLAYBOOK_PATH}/${config.folderName} && cp ~/ansible.cfg ansible.cfg && sudo ansible-playbook -i ${PLAYBOOK_PATH}/${config.folderName}/inventory/${config.envName} ${PLAYBOOK_PATH}/${config.folderName}/rabbitmq.yml --tags update --vault-password-file  ~/.agv"
                            }
                        }
                    }
                }
                stage ('redis') {
                    
                    steps {
                        script {
                            if (params.REQUESTED_ACTION == 'redis'){
                                echo 'Depoying redis'
                                sh "cd ${PLAYBOOK_PATH}/${config.folderName} && cp ~/ansible.cfg ansible.cfg && sudo ansible-playbook -i ${PLAYBOOK_PATH}/${config.folderName}/inventory/${config.envName} ${PLAYBOOK_PATH}/${config.folderName}/redis.yml --tags update --vault-password-file  ~/.agv"
                            }
                        }
                        
                    }
                }
                stage ('tusker') {
                    
                    steps {
                        script {
                            if (params.REQUESTED_ACTION == 'tusker'){
                                echo 'Depoying  tusker'
                                sh "cd ${PLAYBOOK_PATH}/${config.folderName} && cp ~/ansible.cfg ansible.cfg && sudo ansible-playbook -i ${PLAYBOOK_PATH}/${config.folderName}/inventory/${config.envName} ${PLAYBOOK_PATH}/${config.folderName}/tusker.yml --tags update --vault-password-file  ~/.agv"
                            }
                        }
                    }
                }
                stage ('pheme') {
                    
                    steps {
                        script {
                            if (params.REQUESTED_ACTION == 'pheme'){
                                echo 'Depoying  pheme'
                                sh "cd ${PLAYBOOK_PATH}/${config.folderName} && cp ~/ansible.cfg ansible.cfg && sudo ansible-playbook -i ${PLAYBOOK_PATH}/${config.folderName}/inventory/${config.envName} ${PLAYBOOK_PATH}/${config.folderName}/pheme.yml --tags update --vault-password-file  ~/.agv"
                            }
                        }
                    }
                }
                stage ('droms') {
                    
                    steps {
                        script {
                            if (params.REQUESTED_ACTION == 'droms'){
                                echo 'Depoying droms'
                                sh "cd ${PLAYBOOK_PATH}/${config.folderName} && cp ~/ansible.cfg ansible.cfg && sudo ansible-playbook -i ${PLAYBOOK_PATH}/${config.folderName}/inventory/${config.envName} ${PLAYBOOK_PATH}/${config.folderName}/droms.yml --tags update --vault-password-file  ~/.agv"
                            }
                        }
                    }
                }
                stage ('openadr2b') {
                    
                    steps {
                        script {
                            if (params.REQUESTED_ACTION == 'openadr2b'){
                                echo 'Depoying openadr2b'
                                sh "cd ${PLAYBOOK_PATH}/${config.folderName} && cp ~/ansible.cfg ansible.cfg && sudo ansible-playbook -i ${PLAYBOOK_PATH}/${config.folderName}/inventory/${config.envName} ${PLAYBOOK_PATH}/${config.folderName}/openadr2b.yml --tags update --vault-password-file  ~/.agv"
                            }
                        }
                    }
                }
                stage ('ceep') {
                    
                    steps {
                        script {
                            if (params.REQUESTED_ACTION == 'ceep'){
                                echo 'Depoying ceep'
                                sh "cd ${PLAYBOOK_PATH}/${config.folderName} && cp ~/ansible.cfg ansible.cfg && sudo ansible-playbook -i ${PLAYBOOK_PATH}/${config.folderName}/inventory/${config.envName} ${PLAYBOOK_PATH}/${config.folderName}/ceep.yml --tags update --vault-password-file  ~/.agv"
                            }
                        }
                    }
                }
                stage ('dianoga') {
                    
                    steps {
                        script {
                            if (params.REQUESTED_ACTION == 'dianoga'){
                                echo 'Depoying dianoga'
                                sh "cd ${PLAYBOOK_PATH}/${config.folderName} && cp ~/ansible.cfg ansible.cfg && sudo ansible-playbook -i ${PLAYBOOK_PATH}/${config.folderName}/inventory/${config.envName} ${PLAYBOOK_PATH}/${config.folderName}/dianoga.yml --tags update --vault-password-file  ~/.agv"
                            }
                        }
                    }
                }
                stage ('rtcc') {
                    
                    steps {
                        script {
                            if (params.REQUESTED_ACTION == 'rtcc'){
                                echo 'Depoying rtcc'
                                sh "cd ${PLAYBOOK_PATH}/${config.folderName} && cp ~/ansible.cfg ansible.cfg && sudo ansible-playbook -i ${PLAYBOOK_PATH}/${config.folderName}/inventory/${config.envName} ${PLAYBOOK_PATH}/${config.folderName}/rtcc.yml --tags update --vault-password-file  ~/.agv"
                            }
                        }
                    }
                }
                stage ('cascade') {
                    
                    steps {
                        script {
                            if (params.REQUESTED_ACTION == 'cascade'){
                                echo 'Depoying cascade'
                                sh "cd ${PLAYBOOK_PATH}/${config.folderName} && cp ~/ansible.cfg ansible.cfg && sudo ansible-playbook -i ${PLAYBOOK_PATH}/${config.folderName}/inventory/${config.envName} ${PLAYBOOK_PATH}/${config.folderName}/cascade.yml --tags update --vault-password-file  ~/.agv"
                            }
                        }
                    }
                }
                stage ('fam-backend') {
                    
                    steps {
                        script {
                            if (params.REQUESTED_ACTION == 'fam-backend'){
                                echo 'Depoying fam-backend'
                                sh "cd ${PLAYBOOK_PATH}/${config.folderName} && cp ~/ansible.cfg ansible.cfg && sudo ansible-playbook -i ${PLAYBOOK_PATH}/${config.folderName}/inventory/${config.envName} ${PLAYBOOK_PATH}/${config.folderName}/fam-backend.yml --tags=configs,restart --vault-password-file  ~/.agv"
                            }
                        }
                    }
                }



            }
        }
    }

