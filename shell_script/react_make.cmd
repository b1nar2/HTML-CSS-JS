@rem react project maker

@rem 인자: 생성할 프로젝트명
echo %1

@rem 워크스페이스 드라이브 이동(같은 드라이브에 있으면 불필요)
e:

@rem 워크스페이스로 이동(cd)
cd E:\KSZ\works\js\React

@rem react proect 생성(make)
npx create-react-app %1